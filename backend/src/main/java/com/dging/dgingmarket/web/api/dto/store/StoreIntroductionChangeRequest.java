package com.dging.dgingmarket.web.api.dto.store;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 상점 소개글 변경 요청 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreIntroductionChangeRequest {

    @Size(max = 1000)
    private String introduction;
}
