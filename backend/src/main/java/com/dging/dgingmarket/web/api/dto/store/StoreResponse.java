package com.dging.dgingmarket.web.api.dto.store;

import com.dging.dgingmarket.util.constant.DocumentDescriptions;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

/**
 * 상점 상세 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "상점 상세 조회 응답 DTO")
public class StoreResponse {

    @Schema(description = RESPONSE_ID, example = EXAMPLE_ID)
    private Long id;

    @Schema(description = RESPONSE_USER_ID, example = EXAMPLE_ID)
    private Long userId;

    @Schema(description = RESPONSE_STORE_NAME, example = EXAMPLE_STORE_NAME)
    private String name;

    @Schema(description = RESPONSE_STORE_INTRODUCTION, example = EXAMPLE_STORE_INTRODUCTION)
    private String introduction;

    @Schema(description = RESPONSE_PROFILE_IMAGE_URL, example = EXAMPLE_PROFILE_IMAGE_URL)
    private String profileImageUrl;

    @Schema(description = RESPONSE_RATING, example = EXAMPLE_RATING)
    private Float rating;

    @Schema(description = RESPONSE_SALES_COUNT, example = EXAMPLE_SALES_COUNT)
    private int salesCount;

    @Schema(description = RESPONSE_IS_AUTHENTICATED, example = EXAMPLE_IS_AUTHENTICATED)
    private Boolean isAuthenticated;

    @Schema(description = RESPONSE_PRODUCTS_TOTAL_COUNT, example = EXAMPLE_PRODUCTS_TOTAL_COUNT)
    private int productsTotalCount;

    @Schema(description = RESPONSE_REVIEWS_TOTAL_COUNT, example = EXAMPLE_REVIEWS_TOTAL_COUNT)
    private int reviewsTotalCount;

    @Schema(description = RESPONSE_FAVORITE_PRODUCTS_TOTAL_COUNT, example = EXAMPLE_FAVORITE_PRODUCTS_TOTAL_COUNT)
    private int favoriteProductsTotalCount;

    @Schema(description = RESPONSE_FOLLOWINGS_TOTAL_COUNT, example = EXAMPLE_FOLLOWINGS_TOTAL_COUNT)
    private int followingsTotalCount;

    @Schema(description = RESPONSE_FOLLOWERS_TOTAL_COUNT, example = EXAMPLE_FOLLOWERS_TOTAL_COUNT)
    private int followersTotalCount;

    @Schema(description = RESPONSE_CREATED_AT, example = EXAMPLE_CREATED_AT)
    private Date createdAt;

    @QueryProjection
    public StoreResponse(Long id, Long userId, String name, String introduction, String profileImageUrl, Float rating, int salesCount, Boolean isAuthenticated, int reviewsTotalCount, int favoriteProductsTotalCount, int followingsTotalCount, int followersTotalCount, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.introduction = introduction;
        this.profileImageUrl = profileImageUrl;
        this.rating = rating;
        this.salesCount = salesCount;
        this.productsTotalCount = salesCount;
        this.isAuthenticated = isAuthenticated;
        this.reviewsTotalCount = reviewsTotalCount;
        this.favoriteProductsTotalCount = favoriteProductsTotalCount;
        this.followingsTotalCount = followingsTotalCount;
        this.followersTotalCount = followersTotalCount;
        this.createdAt = createdAt;
    }
}
