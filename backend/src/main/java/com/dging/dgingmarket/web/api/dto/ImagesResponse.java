package com.dging.dgingmarket.web.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

@Data
@Schema(description = "이미지 응답 DTO")
public class ImagesResponse {

    @Schema(description = RESPONSE_ID, example = EXAMPLE_ID)
    private Long id;

    @Schema(description = RESPONSE_IMAGE_URL, example = EXAMPLE_IMAGE_URL)
    private String url;

    @Builder
    @QueryProjection
    public ImagesResponse(Long id, String url) {
        this.id = id;
        this.url = url;
    }
}
