package com.dging.dgingmarket.client.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
public class KakaoProfile {
    private Long id;
    private Properties properties;
    private KakaoAccount kakao_account;

    @Getter
    @ToString
    public static class KakaoAccount {
        private Profile profile;
        private String name;
        private String email;

        @Getter
        @ToString
        public static class Profile {
            private String profile_image_url;
        }
    }

    @Getter
    @ToString
    public static class Properties {
        private String nickname;
    }
}