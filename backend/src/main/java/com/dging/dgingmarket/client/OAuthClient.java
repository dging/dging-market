package com.dging.dgingmarket.client;

import com.dging.dgingmarket.dto.OAuthTokenResponse;
import com.dging.dgingmarket.dto.SocialProfile;
import com.dging.dgingmarket.enums.SocialType;

public interface OAuthClient {

    String getOauthRedirectURL();

    OAuthTokenResponse getTokenInfo(String code);

    SocialProfile getProfile(String accessToken);

    default SocialType type() {
        if (this instanceof FacebookOAuthClient) {
            return SocialType.FACEBOOK;
        } else if (this instanceof GoogleOAuthClient) {
            return SocialType.GOOGLE;
        } else if (this instanceof NaverOAuthClient) {
            return SocialType.NAVER;
        } else if (this instanceof KaKaoOAuthClient) {
            return SocialType.KAKAO;
        } else {
            return null;
        }
    }
}
