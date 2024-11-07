package com.dging.dgingmarket.domain.store;

import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.store.StoreProductReviewsResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryRepository {
    Page<StoreProductReviewsResponse> storeProductReviews(Long id, Pageable pageable, @Valid CommonCondition cond);
}
