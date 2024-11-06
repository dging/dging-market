package com.dging.dgingmarket.web.api.dto.common;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

@Data
@Builder
@Schema(description = "이미지 응답 DTO")
public class ImagesResponse {

    @Schema(description = RESPONSE_ID)
    private Long id;

    @Schema(description = RESPONSE_IMAGE_URL, example = EXAMPLE_IMAGE_URL)
    private String url;

    @QueryProjection
    public ImagesResponse(Long id, String url) {
        this.id = id;
        this.url = url;
    }
}
