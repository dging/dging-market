package com.dging.dgingmarket.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SocialProfile {

    private String snsId;
    private String email;
    private String username;
    private String profileImageUrl;
}
