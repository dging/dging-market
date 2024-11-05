package com.dging.dgingmarket.web.api.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.REQUEST_ACCESS_TOKEN;
import static com.dging.dgingmarket.util.constant.DocumentDescriptions.REQUEST_REFRESH_TOKEN;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Schema(description = "토큰 요청 DTO")
public class TokenRequest {

    @NotEmpty
    @Schema(description = REQUEST_ACCESS_TOKEN)
    private String accessToken;

    @NotEmpty
    @Schema(description = REQUEST_REFRESH_TOKEN)
    private String refreshToken;
}
