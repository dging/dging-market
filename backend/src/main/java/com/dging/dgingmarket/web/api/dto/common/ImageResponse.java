package com.dging.dgingmarket.web.api.dto.common;

import com.dging.dgingmarket.domain.common.Image;
import com.dging.dgingmarket.util.enums.ImageType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.util.ObjectUtils;

import java.util.Date;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "이미지 상세 조회 응답 DTO")
public class ImageResponse {

    @Schema(description = RESPONSE_ID)
    private Long id;

    @Schema(description = RESPONSE_IMAGE_TYPE)
    private ImageType type;

    @Schema(description = RESPONSE_FILE_NAME)
    private String fileName;

    @Schema(description = RESPONSE_FILE_PATH)
    private String path;

    @Schema(description = RESPONSE_IMAGE_URL)
    private String url;

    @Schema(description = RESPONSE_FILE_SIZE)
    private int size;

    @Schema(description = RESPONSE_CREATED_AT)
    private Date createdAt;

    public String getType() {
        if(ObjectUtils.isEmpty(type)) {
            return null;
        } else {
            return type.getValue();
        }
    }

    public static ImageResponse of(Image image) {
        return ImageResponse.builder()
                .id(image.getId())
                .type(image.getType())
                .fileName(image.getFileName())
                .path(image.getPath())
                .url(image.getUrl())
                .size(image.getSize())
                .createdAt(image.getCreatedAt())
                .build();
    }
}
