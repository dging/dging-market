package com.dging.dgingmarket.client;

import com.dging.dgingmarket.client.dto.FacebookProfile;
import com.dging.dgingmarket.client.dto.OAuthTokenResponse;
import com.dging.dgingmarket.client.dto.SocialProfile;
import com.dging.dgingmarket.exception.social.CSocialException.CSocialCommunicationException;
import com.dging.dgingmarket.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Qualifier(value = "dgingmarket")
@RequiredArgsConstructor
public class FacebookOAuthClient implements OAuthClient {

    private final WebClient webClient;

    private final UrlService urlService;

    @Value("${dging-market.social.facebook.client-id}")
    private String facebookClientId;

    @Value("${dging-market.social.facebook.client-secret}")
    private String facebookClientSecret;

    @Value("${dging-market.social.facebook.redirect}")
    private String facebookRedirectUri;

    @Value("${dging-market.social.facebook.url.login}")
    private String facebookLoginUrl;

    @Value("${dging-market.social.facebook.url.token}")
    private String facebookTokenUrl;

    @Value("${dging-market.social.facebook.url.profile}")
    private String facebookProfileUrl;

    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();

        params.put("redirect_uri", urlService.getServerBaseUrl() + facebookRedirectUri);
        params.put("client_id", facebookClientId);
        params.put("scope", "email,public_profile");

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        return facebookLoginUrl + "?" + parameterString;
    }

    @Override
    public OAuthTokenResponse getTokenInfo(String code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", facebookClientId);
        params.add("client_secret", facebookClientSecret);
        params.add("redirect_uri", urlService.getServerBaseUrl() + facebookRedirectUri);
        params.add("code", code);

        return webClient.get()
                .uri(facebookTokenUrl, builder -> builder.queryParams(params).build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new CSocialCommunicationException()))
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new CSocialCommunicationException()))
                .bodyToMono(OAuthTokenResponse.class)
                .block();
    }

    @Override
    public SocialProfile getProfile(String accessToken) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("fields", "name,picture,email");
        params.add("access_token", accessToken);

        FacebookProfile facebookProfile = webClient.get()
                .uri(facebookProfileUrl, builder -> builder.queryParams(params).build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new CSocialCommunicationException()))
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new CSocialCommunicationException()))
                .bodyToMono(FacebookProfile.class)
                .block();

        return new SocialProfile(facebookProfile.getId(), facebookProfile.getEmail(), facebookProfile.getName(), facebookProfile.getPicture().getData().getUrl());
    }
}
