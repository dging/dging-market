package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.util.constant.ValidationMessages;
import com.dging.dgingmarket.util.enums.RunningStatus;
import com.dging.dgingmarket.util.validation.Enum;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상품 진행상태 변경 요청 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductRunningStatusChangeRequest {

    @NotEmpty
    @Enum(enumClass = RunningStatus.class, ignoreCase = true, message = ValidationMessages.RUNNING_STATUS)
    private String runningStatus;

    public RunningStatus getRunningStatus() {
        return RunningStatus.find(runningStatus);
    }
}
