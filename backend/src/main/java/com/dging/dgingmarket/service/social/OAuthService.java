package com.dging.dgingmarket.service.social;

import com.dging.dgingmarket.client.OAuthClient;
import com.dging.dgingmarket.dto.OAuthTokenResponse;
import com.dging.dgingmarket.dto.SocialProfile;
import com.dging.dgingmarket.enums.SocialType;
import com.dging.dgingmarket.domain.common.exception.InvalidSocialTypeException;
import com.dging.dgingmarket.domain.common.exception.SocialCommunicationException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class OAuthService {


    private final List<OAuthClient> socialOAuthList;
    private final HttpServletResponse response;

    @Autowired
    public OAuthService(
            List<OAuthClient> socialOAuthList,
            HttpServletResponse response
    ) {
        this.socialOAuthList = socialOAuthList;
        this.response = response;
    }

    public String oauthRedirectURL(SocialType socialType) {
        OAuthClient oauthClient = this.findSocialOauthByType(socialType);
        return oauthClient.getOauthRedirectURL();
    }

    public void request(SocialType socialType) {
        OAuthClient oauthClient = this.findSocialOauthByType(socialType);
        String redirectURL = oauthClient.getOauthRedirectURL();
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            log.error(e.toString());
            throw SocialCommunicationException.EXCEPTION;
        }
    }

    public OAuthTokenResponse tokenInfo(SocialType socialType, String code) {
        OAuthClient socialOauth = this.findSocialOauthByType(socialType);
        return socialOauth.getTokenInfo(code);
    }

    public SocialProfile profile(SocialType socialType, String accessToken) {
        OAuthClient socialOauth = this.findSocialOauthByType(socialType);
        return socialOauth.getProfile(accessToken);
    }

    private OAuthClient findSocialOauthByType(SocialType socialType) {
        return socialOAuthList.stream()
                .filter(x -> x.type() == socialType)
                .findFirst()
                .orElseThrow(InvalidSocialTypeException::new);
    }

}
