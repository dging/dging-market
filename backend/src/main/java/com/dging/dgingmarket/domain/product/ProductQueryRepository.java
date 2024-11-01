package com.dging.dgingmarket.domain.product;

import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.product.ProductsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryRepository {

    Page<ProductsResponse> products(Pageable pageable, CommonCondition cond);
}
