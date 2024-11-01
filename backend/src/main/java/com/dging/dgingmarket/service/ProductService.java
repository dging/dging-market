package com.dging.dgingmarket.service;

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

        List<String> requestTagNames = request.getTags();
        List<Tag> foundTags = tagRepository.findByNameIn(requestTagNames);
        ArrayList<Tag> tagsToCreate = new ArrayList<>();

        for (String requestTagName : requestTagNames) {
            Tag tag = foundTags.stream()
                    .filter(v -> Objects.equals(requestTagName, v.getName()))
                    .findFirst()
                    .orElse(Tag.create(requestTagName));
            tagsToCreate.add(tag);
        }

        return request.toEntityWith(store, tagsToCreate);
    }
}
