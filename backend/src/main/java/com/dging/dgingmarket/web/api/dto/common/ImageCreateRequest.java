package com.dging.dgingmarket.web.api.dto.common;

import com.dging.dgingmarket.domain.common.enums.ImageType;
import com.dging.dgingmarket.util.constant.ValidationMessages;
import com.dging.dgingmarket.util.validation.Enum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "이미지 요청 DTO")
public class ImageCreateRequest {

    @NotNull
    @Schema(description = "이미지 요청 DTO")
    private MultipartFile image;

    @NotEmpty
    @Enum(enumClass = ImageType.class, ignoreCase = true, message = ValidationMessages.IMAGE_TYPE)
    @Schema(description = "이미지 요청 DTO")
    private String type;

    @JsonIgnore
    private String url;

    @JsonIgnore
    private String fileName;

    public ImageType getType() {
        return ImageType.find(type);
    }
}
