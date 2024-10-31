package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.util.enums.ProductQuality;
import com.dging.dgingmarket.util.enums.RunningStatus;
import com.dging.dgingmarket.util.validation.Enum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

/**
 * 상품 진행상태 변경 요청 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductRunningStatusChangeRequest {

    @NotEmpty
    private String seq;

    @NotEmpty
    @Enum(enumClass = RunningStatus.class, ignoreCase = true, message = "[판매중, 예약중, 거래완료] 중에 하나이어야 합니다.")
    private RunningStatus runningStatus;
}
