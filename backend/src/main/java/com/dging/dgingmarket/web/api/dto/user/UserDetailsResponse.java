package com.dging.dgingmarket.web.api.dto.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserDetailsResponse {

    private String seq;
    private String userId;
    private String username;
    private String provider;
    private Date registeredAt;
}
