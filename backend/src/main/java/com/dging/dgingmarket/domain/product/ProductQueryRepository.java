package com.dging.dgingmarket.domain.product;

import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.product.ProductResponse;
import com.dging.dgingmarket.web.api.dto.product.ProductsResponse;
import com.dging.dgingmarket.web.api.dto.product.StoreProductsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductQueryRepository {

    Page<ProductsResponse> products(Pageable pageable, CommonCondition cond);
    Optional<ProductResponse> product(Long id);
    Page<StoreProductsResponse> storeProducts(Pageable pageable, Long storeId, CommonCondition cond);
}
