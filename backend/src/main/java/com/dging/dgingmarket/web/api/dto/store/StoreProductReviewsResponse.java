package com.dging.dgingmarket.web.api.dto.store;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

/**
 * 상점 상품 후기 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "상점 상품 후기 조회 응답 DTO")
public class StoreProductReviewsResponse {

    @Schema(description = RESPONSE_ID, example = EXAMPLE_ID)
    private Long id;

    @Schema(description = RESPONSE_STORE_ID, example = EXAMPLE_ID)
    private Long storeId;

    @Schema(description = RESPONSE_STORE_NAME, example = EXAMPLE_STORE_NAME)
    private String storeName;

    @Schema(description = RESPONSE_PROFILE_IMAGE_URL, example = EXAMPLE_PROFILE_IMAGE_URL)
    private String profileImageUrl;

    @Schema(description = RESPONSE_RATING, example = EXAMPLE_RATING)
    private int rating;

    @Schema(description = RESPONSE_PRODUCT_ID, example = EXAMPLE_ID)
    private Long productId;

    @Schema(description = RESPONSE_PRODUCT_NAME, example = EXAMPLE_PRODUCT_TITLE)
    private String productName;

    @Schema(description = RESPONSE_REVIEW_CONTENT, example = EXAMPLE_REVIEW_CONTENT)
    private String content;

    @Schema(description = RESPONSE_CREATED_AT, example = EXAMPLE_CREATED_AT)
    private Date createdAt;

    @QueryProjection
    public StoreProductReviewsResponse(Long id, Long storeId, String storeName, String profileImageUrl, int rating, Long productId, String productName, String content, Date createdAt) {
        this.id = id;
        this.storeId = storeId;
        this.storeName = storeName;
        this.profileImageUrl = profileImageUrl;
        this.rating = rating;
        this.productId = productId;
        this.productName = productName;
        this.content = content;
        this.createdAt = createdAt;
    }
}
