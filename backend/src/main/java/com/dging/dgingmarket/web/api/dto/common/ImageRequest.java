package com.dging.dgingmarket.web.api.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ImageRequest {

    @NotNull
    private MultipartFile image;

    @NotEmpty
    private String type;

    @JsonIgnore
    private String url;

    @JsonIgnore
    private String fileName;
}
