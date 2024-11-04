package com.dging.dgingmarket.web.api.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

@Getter @Setter
@AllArgsConstructor
@Schema(description = "토큰 응답 DTO")
public class TokenResponse {

    @Schema(description = RESPONSE_GRANT_TYPE)
    private String grantType;

    @Schema(description = RESPONSE_ACCESS_TOKEN)
    private String accessToken;

    @Schema(description = RESPONSE_REFRESH_TOKEN)
    private String refreshToken;

    @Schema(description = RESPONSE_ACCESS_TOKEN_EXPIRES_IN)
    private Long accessTokenExpiresIn;
}
