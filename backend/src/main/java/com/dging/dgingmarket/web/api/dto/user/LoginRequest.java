package com.dging.dgingmarket.web.api.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    @NotEmpty
    private String id;

    @Length(min = 8, max = 64)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$")
    @NotEmpty
    private String password;

    private String provider;

    public LoginRequest(String id, String password, String provider) {
        this.id = id;
        this.password = password;
        this.provider = provider;
    }

    public LoginRequest(String id, String password) {
        this.id = id;
        this.password = password;
    }
}