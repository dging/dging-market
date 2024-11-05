package com.dging.dgingmarket.domain.store;

import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.product.FavoriteProductsResponse;
import com.dging.dgingmarket.web.api.dto.product.ProductResponse;
import com.dging.dgingmarket.web.api.dto.product.ProductsResponse;
import com.dging.dgingmarket.web.api.dto.product.StoreProductsResponse;
import com.dging.dgingmarket.web.api.dto.store.FollowersResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FollowerQueryRepository {

    Page<FollowersResponse> followers(Pageable pageable, Long storeId, CommonCondition cond);
}
