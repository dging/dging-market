package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.util.constant.DocumentDescriptions;
import com.dging.dgingmarket.util.constant.ValidationMessages;
import com.dging.dgingmarket.util.enums.RunningStatus;
import com.dging.dgingmarket.util.validation.Enum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상품 진행상태 변경 요청 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "상품 진행 상태 변경 요청 DTO")
public class ProductRunningStatusChangeRequest {

    @NotEmpty
    @Enum(enumClass = RunningStatus.class, ignoreCase = true, message = ValidationMessages.RUNNING_STATUS)
    @Schema(description = DocumentDescriptions.REQUEST_RUNNING_STATUS)
    private String runningStatus;

    public RunningStatus getRunningStatus() {
        return RunningStatus.find(runningStatus);
    }
}
