package com.dging.dgingmarket.web.api.dto.store;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상점명 변경 요청 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreNameChangeRequest {

    @Size(max = 20)
    private String name;
}
