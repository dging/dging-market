package com.dging.dgingmarket.web.api.dto.store;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 상점 정보 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreOverviewResponse {

    private Long id;
    private String name;
    private int salesCount;
    private int followersCount;
    List<StoreReviewsResponse> reviews;
}
