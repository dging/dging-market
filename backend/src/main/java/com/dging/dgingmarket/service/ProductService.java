package com.dging.dgingmarket.service;

import com.dging.dgingmarket.domain.common.Image;
import com.dging.dgingmarket.domain.common.ImageRepository;
import com.dging.dgingmarket.domain.common.Tag;
import com.dging.dgingmarket.domain.common.TagRepository;
import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.product.ProductRepository;
import com.dging.dgingmarket.domain.store.Store;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.exception.business.CEntityNotFoundException.CProductNotFoundException;
import com.dging.dgingmarket.exception.business.CInvalidValueException.CUserOwnProductException;
import com.dging.dgingmarket.util.EntityUtils;
import com.dging.dgingmarket.util.enums.RunningStatus;
import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
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

    @Transactional
    public void create(ProductCreateRequest request) {

        final List<Image> imagesToCreate = generateProductImages(request.getImageIds());
        final List<Tag> tagsToCreate = generateProductTags(request.getTags());
        Product productToCreate = generateProduct(request, imagesToCreate, tagsToCreate);
        productRepository.save(productToCreate);
    }

    @Transactional
    public void update(ProductUpdateRequest request) {

        Product foundProduct = productRepository.findByIdAndDeletedIsFalse(request.getId()).orElseThrow(CProductNotFoundException::new);

        User user = EntityUtils.userThrowable();

        if (!Objects.equals(foundProduct.getStoreId(), user.getStore().getId())) {
            throw new CUserOwnProductException();
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

    public ProductResponse product(Long id) {
        return productRepository.product(id).orElseThrow(CProductNotFoundException::new);
    }

    public Page<StoreProductsResponse> storeProducts(Pageable pageable, Long storeId, CommonCondition cond) {
        return productRepository.storeProducts(pageable, storeId, cond);
    }

    @Transactional
    public void delete(Long id) {

        User user = EntityUtils.userThrowable();

        Product foundProduct = productRepository.findByIdAndDeletedIsFalse(id).orElseThrow(CProductNotFoundException::new);

        if (!Objects.equals(foundProduct.getStoreId(), user.getStore().getId())) {
            throw new CUserOwnProductException();
        }

        foundProduct.delete();
    }

    @Transactional
    public void changeRunningStatus(Long id, RunningStatus runningStatus) {

        User user = EntityUtils.userThrowable();

        Product foundProduct = productRepository.findByIdAndDeletedIsFalse(id).orElseThrow(CProductNotFoundException::new);

        if (!Objects.equals(foundProduct.getStoreId(), user.getStore().getId())) {
            throw new CUserOwnProductException();
        }

        foundProduct.changeRunningStatus(runningStatus);
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
            throw new RuntimeException("업로드가 되지 않은 파일이 있는 경우");
        }

        if(foundImages.stream().anyMatch(v -> !Objects.equals(v.getUser().getId(), user.getId()))) {
            throw new RuntimeException("본인이 업로드하지 않은 파일이 있는 경우");
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
