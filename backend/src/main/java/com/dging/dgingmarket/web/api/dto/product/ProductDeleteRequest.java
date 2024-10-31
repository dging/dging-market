package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.util.enums.RunningStatus;
import com.dging.dgingmarket.util.validation.Enum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 상품 삭제 요청 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDeleteRequest {

    @NotEmpty
    private Long id;
}
