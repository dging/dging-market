package com.dging.dgingmarket.domain.store;

import com.dging.dgingmarket.web.api.dto.store.StoreOverviewResponse;
import com.dging.dgingmarket.web.api.dto.store.StoreResponse;

import java.util.Optional;

public interface StoreQueryRepository {

    Optional<StoreOverviewResponse> overview(Long id);

    Optional<StoreResponse> store(Long id);
}
