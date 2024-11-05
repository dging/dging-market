package com.dging.dgingmarket.web.api.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.RESPONSE_ID;
import static com.dging.dgingmarket.util.constant.DocumentDescriptions.RESPONSE_IMAGE_URL;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "이미지 응답 DTO")
public class ImagesResponse {

    @Schema(description = RESPONSE_ID)
    private Long id;

    @Schema(description = RESPONSE_IMAGE_URL)
    private String url;
}
