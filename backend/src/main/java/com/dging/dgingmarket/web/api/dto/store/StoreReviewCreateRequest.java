package com.dging.dgingmarket.web.api.dto.store;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

/**
 * 상점 후기 작성 요청 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "상점 후기 작성 요청 DTO")
public class StoreReviewCreateRequest {

    @NotNull
    @Max(5L)
    @Schema(description = REQUEST_RATING, example = EXAMPLE_RATING)
    private Integer rating;

    @Schema(description = REQUEST_REVIEW_CONTENT, example = EXAMPLE_REVIEW_CONTENT)
    private String content;
}
