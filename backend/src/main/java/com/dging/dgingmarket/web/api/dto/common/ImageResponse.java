package com.dging.dgingmarket.web.api.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.RESPONSE_ID;
import static com.dging.dgingmarket.util.constant.DocumentDescriptions.RESPONSE_IMAGE_URL;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Schema(description = "이미지 응답 DTO")
public class ImageResponse {

    @Schema(description = RESPONSE_ID)
    private Long id;

    @Schema(description = RESPONSE_IMAGE_URL)
    private String url;
}
