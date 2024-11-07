package com.dging.dgingmarket.web.api.dto.store;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

/**
 * 팔로워 조회 응답 DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "팔로워 조회 응답 DTO")
public class FollowersResponse {

    @Schema(description = RESPONSE_ID, example = EXAMPLE_ID)
    private Long storeId;

    @Schema(description = RESPONSE_STORE_NAME, example = EXAMPLE_STORE_NAME)
    private String storeName;

    @Schema(description = RESPONSE_RATING, example = EXAMPLE_RATING)
    private float rating;

    @Schema(description = RESPONSE_SALES_COUNT, example = EXAMPLE_SALES_COUNT)
    private int salesCount;

    @Schema(description = RESPONSE_FOLLOWERS_COUNT, example = EXAMPLE_FOLLOWERS_COUNT)
    private int followersCount;
}
