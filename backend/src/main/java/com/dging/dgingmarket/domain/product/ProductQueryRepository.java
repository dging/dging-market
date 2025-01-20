package com.dging.dgingmarket.domain.product;

import com.dging.dgingmarket.web.api.dto.CommonCondition;
import com.dging.dgingmarket.web.api.dto.product.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductQueryRepository {

    Page<ProductsResponse> products(Pageable pageable, CommonCondition cond);
    Optional<ProductResponse> product(Long id);
    Page<StoreProductsResponse> storeProducts(Pageable pageable, Long storeId, CommonCondition cond, ProductsCondition productsCond);
    Page<FavoriteProductsResponse> favoriteProducts(Pageable pageable, Long userId, CommonCondition cond);
}
