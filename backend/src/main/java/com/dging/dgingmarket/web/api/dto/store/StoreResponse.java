package com.dging.dgingmarket.web.api.dto.store;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 상점 상세 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreResponse {

    private Long id;
    private Long userId;
    private String name;
    private String introduction;
    private String profileImageUrl;
    private Float rating;
    private int salesCount;
    private boolean isAuthenticated;
    private Date createdAt;

    private int productsTotalCount;
    private int reviewsTotalCount;
    private int favoriteProductsTotalCount;
    private int followingsTotalCount;
    private int followersTotalCount;
}
