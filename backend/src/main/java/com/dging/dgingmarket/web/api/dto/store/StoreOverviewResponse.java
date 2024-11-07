package com.dging.dgingmarket.web.api.dto.store;

import com.dging.dgingmarket.util.constant.DocumentDescriptions;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 상점 정보 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "상점 정보 응답 DTO")
public class StoreOverviewResponse {

    @Schema(description = DocumentDescriptions.RESPONSE_ID, example = DocumentDescriptions.EXAMPLE_ID)
    private Long id;

    @Schema(description = DocumentDescriptions.RESPONSE_STORE_NAME, example = DocumentDescriptions.EXAMPLE_STORE_NAME)
    private String name;

    @Schema(description = DocumentDescriptions.RESPONSE_SALES_COUNT, example = DocumentDescriptions.EXAMPLE_SALES_COUNT)
    private int salesCount;

    @Schema(description = DocumentDescriptions.RESPONSE_FOLLOWERS_COUNT, example = DocumentDescriptions.EXAMPLE_FOLLOWERS_COUNT)
    private int followersCount;

    @Schema(description = DocumentDescriptions.RESPONSE_STORE_PRODUCT_REVIEWS, example = "[]")
    List<StoreProductReviewsResponse> reviews;

    @QueryProjection
    public StoreOverviewResponse(Long id, String name, int salesCount, int followersCount) {
        this.id = id;
        this.name = name;
        this.salesCount = salesCount;
        this.followersCount = followersCount;
    }
}
