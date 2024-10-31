package com.dging.dgingmarket.web.api.dto.store;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
