package com.dging.dgingmarket.service;

import com.dging.dgingmarket.domain.common.Image;
import com.dging.dgingmarket.domain.common.ImageRepository;
import com.dging.dgingmarket.domain.common.Tag;
import com.dging.dgingmarket.domain.common.TagRepository;
import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.product.ProductRepository;
import com.dging.dgingmarket.domain.store.Store;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.util.EntityUtils;
import com.dging.dgingmarket.web.api.dto.product.ProductCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        Product productToCreate = generateProduct(request);
        productRepository.save(productToCreate);

        //이미지 업로드(예외처리 포함)
        //이미지 설정

    }

    private Product generateProduct(ProductCreateRequest request) {

        User user = EntityUtils.userThrowable();
        Store store = user.getStore();

        final List<Image> imagesToCreate = new ArrayList<>();
        final List<Tag> tagsToCreate = new ArrayList<>();

        if(request.getImageIds() != null && !request.getImageIds().isEmpty()) {
            imagesToCreate.addAll(generateProductImages(request.getImageIds()));
        }

        if(request.getTags() != null && !request.getTags().isEmpty()) {
            tagsToCreate.addAll(generateProductTags(request.getTags()));
        }

        return request.toEntityWith(store, imagesToCreate, tagsToCreate);
    }

    private List<Image> generateProductImages(List<Long> requestImageIds) {

        User user = EntityUtils.userThrowable();
        List<Image> foundImages = imageRepository.findByIdIn(requestImageIds);

        if(foundImages.isEmpty()) {
            throw new RuntimeException("요청에 대응하는 결과가 없는 경우");
        }

        if(foundImages.stream().noneMatch(v -> Objects.equals(v.getUser().getId(), user.getId()))) {
            throw new RuntimeException("요청에 대응하는 결과는 있지만 본인이 업로드한 파일이 아닌 경우");
        }

        return foundImages;
    }

    private List<Tag> generateProductTags(List<String> requestTagNames) {
        List<Tag> foundTags = tagRepository.findByNameIn(requestTagNames);
        List<Tag> tagsToCreate = new ArrayList<>();

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
