package com.dging.dgingmarket.web.api.dto.user;

import com.dging.dgingmarket.util.constant.DocumentDescriptions;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "소셜 로그인 요청 DTO")
public class SocialLoginRequest {

    @NotEmpty
    @Schema(description = DocumentDescriptions.REQUEST_SOCIAL_ACCESS_TOKEN, example = DocumentDescriptions.EXAMPLE_SOCIAL_ACCESS_TOKEN)
    private String accessToken;
}
