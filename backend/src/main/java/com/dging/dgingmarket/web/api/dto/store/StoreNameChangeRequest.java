package com.dging.dgingmarket.web.api.dto.store;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * 상점명 변경 요청 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreNameChangeRequest {

    @Size(max = 20)
    private String name;
}
