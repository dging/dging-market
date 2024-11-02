package com.dging.dgingmarket.web.api.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SignupRequest {

    @NotEmpty
    private String businessNumber;

    @NotEmpty
    private String phoneNumber;

    @NotNull
    private Boolean type1Agreement;
    @NotNull
    private Boolean type2Agreement;

}
