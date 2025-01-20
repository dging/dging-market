package com.dging.dgingmarket.web.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.RESPONSE_ID;
import static com.dging.dgingmarket.util.constant.DocumentDescriptions.RESPONSE_TAG_NAME;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Schema(description = "태그 응답 DTO")
public class TagsResponse {

    @Schema(description = RESPONSE_ID)
    private Long id;

    @Schema(description = RESPONSE_TAG_NAME)
    private String name;
}
