package com.dging.dgingmarket.web.api.dto.store;

import com.dging.dgingmarket.web.api.dto.product.RecentProductsResponse;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;
import java.util.List;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

/**
 * 팔로잉 조회 응답 DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "팔로잉 조회 응답 DTO")
public class FollowingsResponse {

    @Schema(description = RESPONSE_STORE_ID, example = EXAMPLE_ID)
    private Long storeId;

    @Schema(description = RESPONSE_STORE_NAME, example = EXAMPLE_STORE_NAME)
    private String storeName;

    @Schema(description = RESPONSE_SALES_COUNT, example = EXAMPLE_SALES_COUNT)
    private int salesCount;

    @Schema(description = RESPONSE_FOLLOWERS_COUNT, example = EXAMPLE_FOLLOWERS_COUNT)
    private int followersCount;

    @Schema(description = RESPONSE_RECENT_PRODUCTS, example = "[]")
    private List<RecentProductsResponse> recentProducts;

    @Schema(description = RESPONSE_RECENT_PRODUCTS, example = "[]")
    private Date createdAt;

    @QueryProjection
    public FollowingsResponse(Long storeId, String storeName, int salesCount, int followersCount, Date createdAt) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.salesCount = salesCount;
        this.followersCount = followersCount;
        this.createdAt = createdAt;
    }
}
