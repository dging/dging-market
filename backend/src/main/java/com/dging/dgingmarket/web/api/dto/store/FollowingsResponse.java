package com.dging.dgingmarket.web.api.dto.store;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 팔로잉 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FollowingsResponse {

    private Long storeId;
    private String storeName;
    private int salesCount;
    private int followersCount;
    private List<String> recentProductImageUrls;
}
