package com.dging.dgingmarket.web.api.dto.product;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 상품 찜 해제 요청 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductFavoriteDeleteRequest {

    @NotEmpty
    private Long id;
}
