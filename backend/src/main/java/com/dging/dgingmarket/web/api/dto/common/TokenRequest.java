package com.dging.dgingmarket.web.api.dto.common;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TokenRequest {

    @NotEmpty
    private String accessToken;
    @NotEmpty
    private String refreshToken;
}
