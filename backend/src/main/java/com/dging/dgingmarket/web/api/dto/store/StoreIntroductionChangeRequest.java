package com.dging.dgingmarket.web.api.dto.store;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.EXAMPLE_STORE_INTRODUCTION;
import static com.dging.dgingmarket.util.constant.DocumentDescriptions.REQUEST_STORE_INTRODUCTION;

/**
 * 상점 소개글 변경 요청 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "상점 소개글 변경 응답 DTO")
public class StoreIntroductionChangeRequest {

    @Size(max = 1000)
    @Schema(description = REQUEST_STORE_INTRODUCTION, example = EXAMPLE_STORE_INTRODUCTION)
    private String introduction;
}
