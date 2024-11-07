package com.dging.dgingmarket.web.api.dto.store;

import com.dging.dgingmarket.util.constant.DocumentDescriptions;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상점명 변경 요청 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "상점명 변경 요청 DTO")
public class StoreNameChangeRequest {

    @Size(max = 20)
    @Schema(description = DocumentDescriptions.REQUEST_STORE_NAME, example = DocumentDescriptions.EXAMPLE_STORE_NAME)
    private String name;
}
