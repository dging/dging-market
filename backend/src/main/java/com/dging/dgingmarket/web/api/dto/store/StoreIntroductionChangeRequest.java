package com.dging.dgingmarket.web.api.dto.store;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상점 소개글 변경 요청 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreIntroductionChangeRequest {

    @Size(max = 1000)
    private String introduction;
}
