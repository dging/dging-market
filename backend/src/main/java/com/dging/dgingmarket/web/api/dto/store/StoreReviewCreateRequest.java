package com.dging.dgingmarket.web.api.dto.store;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상점 후기 작성 요청 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreReviewCreateRequest {

    @NotEmpty
    private Long storeId;
    @NotNull
    private Integer rating;
    private String content;
}
