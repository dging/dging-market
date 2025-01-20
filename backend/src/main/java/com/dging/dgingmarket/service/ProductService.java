package com.dging.dgingmarket.service;

import com.dging.dgingmarket.domain.Image;
import com.dging.dgingmarket.domain.ImageRepository;
import com.dging.dgingmarket.domain.Tag;
import com.dging.dgingmarket.domain.TagRepository;
import com.dging.dgingmarket.domain.chat.ChatRoom;
import com.dging.dgingmarket.domain.chat.ChatRoomRepository;
import com.dging.dgingmarket.domain.type.RunningStatus;
import com.dging.dgingmarket.exception.SomeFileNotUploadedException;
import com.dging.dgingmarket.exception.SomeFileNotYoursException;
import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.product.ProductRepository;
import com.dging.dgingmarket.domain.product.exception.ProductNotFoundException;
import com.dging.dgingmarket.domain.store.Store;
import com.dging.dgingmarket.domain.store.exception.UserOwnProductException;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.domain.user.exception.UserNotFoundException;
import com.dging.dgingmarket.util.EntityUtils;
import com.dging.dgingmarket.web.api.dto.CommonCondition;
import com.dging.dgingmarket.web.api.dto.product.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ImageRepository imageRepository;
    private final TagRepository tagRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public void create(ProductCreateRequest request) {

        final List<Image> imagesToCreate = generateProductImages(request.getImageIds());
        final List<Tag> tagsToCreate = generateProductTags(request.getTags());
        Product productToCreate = generateProduct(request, imagesToCreate, tagsToCreate);
        productRepository.save(productToCreate);
    }

    @Transactional
    public void update(Long id, ProductUpdateRequest request) {

        Product foundProduct = productRepository.findByIdAndDeletedIsFalse(id).orElseThrow(ProductNotFoundException::new);

        User user = EntityUtils.userThrowable();

        if (!Objects.equals(foundProduct.getStoreId(), user.getStore().getId())) {
            throw UserOwnProductException.EXCEPTION;
        }

        final List<Image> imagesToCreate = generateProductImages(request.getImageIds());
        final List<Tag> tagsToCreate = generateProductTags(request.getTags());

        foundProduct.update(
                request.getTitle(),
                request.getContent(),
                request.getMainCategory(),
                foundProduct.getMiddleCategory(),
                foundProduct.getSubCategory(),
                imagesToCreate,
                tagsToCreate,
                foundProduct.getPrice(),
                request.getQuality(),
                foundProduct.getQuantity(),
                foundProduct.isAllowsOffers(),
                foundProduct.getRegion(),
                foundProduct.getLocation(),
                foundProduct.isDirectTradeAvailable(),
                foundProduct.isShippingFreeIncluded()
        );
    }

    public Page<ProductsResponse> products(Pageable pageable, CommonCondition cond) {
        return productRepository.products(pageable, cond);
    }

    @Transactional
    public ProductResponse product(Long id) {

        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.countView();

        return productRepository.product(id).orElseThrow(ProductNotFoundException::new);
    }

    public Page<StoreProductsResponse> storeProducts(Pageable pageable, Long storeId, CommonCondition cond, ProductsCondition productsCond) {
        return productRepository.storeProducts(pageable, storeId, cond, productsCond);
    }

    @Transactional
    public void delete(Long id) {

        User user = EntityUtils.userThrowable();

        Product foundProduct = productRepository.findByIdAndDeletedIsFalse(id).orElseThrow(ProductNotFoundException::new);

        if (!Objects.equals(foundProduct.getStoreId(), user.getStore().getId())) {
            throw UserOwnProductException.EXCEPTION;
        }

        foundProduct.delete();
    }

    @Transactional
    public void changeRunningStatus(Long id, RunningStatus runningStatus) {

        User user = EntityUtils.userThrowable();

        Product foundProduct = productRepository.findByIdAndDeletedIsFalse(id).orElseThrow(ProductNotFoundException::new);

        if (!Objects.equals(foundProduct.getStoreId(), user.getStore().getId())) {
            throw UserOwnProductException.EXCEPTION;
        }

        foundProduct.changeRunningStatus(runningStatus);
    }

    @Transactional
    public void createFavorite(Long productId) {

        Product foundProduct = productRepository.findByIdAndDeletedIsFalse(productId).orElseThrow(ProductNotFoundException::new);

        User user = EntityUtils.userThrowable();
        User foundUser = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);

        foundUser.toFavorite(foundProduct);
    }

    @Transactional
    public void deleteFavorite(Long productId) {

        Product foundProduct = productRepository.findByIdAndDeletedIsFalse(productId).orElseThrow(ProductNotFoundException::new);

        User user = EntityUtils.userThrowable();
        User foundUser = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);

        foundUser.toUnfavorite(foundProduct);
    }

    public Page<FavoriteProductsResponse> favoriteProducts(Pageable pageable, CommonCondition cond) {

        User user = EntityUtils.userThrowable();

        return productRepository.favoriteProducts(pageable, user.getId(), cond);
    }

    @Transactional
    public void createPayment(Long productId) {
        Product foundProduct = productRepository.findByIdAndDeletedIsFalse(productId).orElseThrow(ProductNotFoundException::new);

        User purchaser = EntityUtils.userThrowable();
        User foundPurchaser = userRepository.findById(purchaser.getId()).orElseThrow(UserNotFoundException::new);
        User seller = foundProduct.getStore().getUser();

        ChatRoom chatRoomToCreate = ChatRoom.create(foundPurchaser, seller, foundProduct);
        chatRoomRepository.save(chatRoomToCreate);
    }

    private Product generateProduct(ProductCreateRequest request, List<Image> imagesToCreate, List<Tag> tagsToCreate) {

        User user = EntityUtils.userThrowable();
        Store store = user.getStore();

        return request.toEntityWith(store, imagesToCreate, tagsToCreate);
    }

    private List<Image> generateProductImages(List<Long> requestImageIds) {

        final List<Image> imagesToCreate = new ArrayList<>();

        if(requestImageIds == null || requestImageIds.isEmpty()) {
            return imagesToCreate;
        }

        User user = EntityUtils.userThrowable();
        List<Image> foundImages = imageRepository.findByIdIn(requestImageIds);

        if(foundImages.size() < requestImageIds.size()) {
            throw SomeFileNotUploadedException.EXCEPTION;
        }

        if(foundImages.stream().anyMatch(v -> !Objects.equals(v.getUser().getId(), user.getId()))) {
            throw SomeFileNotYoursException.EXCEPTION;
        }

        for (Long requestImageId : requestImageIds) {
            foundImages.stream()
                    .filter(v -> Objects.equals(v.getId(), requestImageId))
                    .findAny()
                    .ifPresent(imagesToCreate::add);
        }

        return imagesToCreate;
    }

    private List<Tag> generateProductTags(List<String> requestTagNames) {

        final List<Tag> tagsToCreate = new ArrayList<>();

        if(requestTagNames == null || requestTagNames.isEmpty()) {
            return tagsToCreate;
        }

        List<Tag> foundTags = tagRepository.findByNameIn(requestTagNames);

        for (String requestTagName : requestTagNames) {
            Tag tagToCreate = foundTags.stream()
                    .filter(tag -> Objects.equals(requestTagName, tag.getName()))
                    .findFirst()
                    .orElse(Tag.create(requestTagName));
            tagsToCreate.add(tagToCreate);
        }
        return tagsToCreate;
    }
}
