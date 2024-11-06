package com.dging.dgingmarket.domain.store;

import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.store.FollowersResponse;
import com.dging.dgingmarket.web.api.dto.store.FollowingsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowerQueryRepository {

    Page<FollowersResponse> followers(Pageable pageable, Long storeId, CommonCondition cond);
    Page<FollowingsResponse> followings(Pageable pageable, Long storeId, CommonCondition cond);
}
