package com.dging.dgingmarket.client;

import com.dging.dgingmarket.dto.NaverProfile;
import com.dging.dgingmarket.dto.OAuthTokenResponse;
import com.dging.dgingmarket.dto.SocialProfile;
import com.dging.dgingmarket.domain.common.exception.SocialCommunicationException;
import com.dging.dgingmarket.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
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
public class NaverOAuthClient implements OAuthClient {

    private final WebClient webClient;

    private final UrlService urlService;

    @Value("${dging-market.social.naver.client-id}")
    private String naverClientId;

    @Value("${dging-market.social.naver.client-secret}")
    private String naverClientSecret;

    @Value("${dging-market.social.naver.redirect}")
    private String naverRedirectUri;

    @Value("${dging-market.social.naver.url.login}")
    private String naverLoginUrl;

    @Value("${dging-market.social.naver.url.token}")
    private String naverTokenUrl;

    @Value("${dging-market.social.naver.url.profile}")
    private String naverProfileUrl;

    @Override
    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();

        params.put("response_type", "code");
        params.put("client_id", naverClientId);
        params.put("redirect_uri", urlService.getServerBaseUrl() + naverRedirectUri);
        params.put("state", RandomStringUtils.random(16, true, true));

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        return naverLoginUrl + "?" + parameterString;
    }

    @Override
    public OAuthTokenResponse getTokenInfo(String code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", naverClientId);
        params.add("client_secret", naverClientSecret);
        params.add("code", code);
        params.add("state", RandomStringUtils.random(16, true, true));

        return webClient.post()
                .uri(naverTokenUrl, builder -> builder.queryParams(params).build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(SocialCommunicationException.EXCEPTION))
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(SocialCommunicationException.EXCEPTION))
                .bodyToMono(OAuthTokenResponse.class)
                .block();
    }

    @Override
    public SocialProfile getProfile(String accessToken) {
        NaverProfile naverProfile = webClient.post()
                .uri(naverProfileUrl)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(SocialCommunicationException.EXCEPTION))
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(SocialCommunicationException.EXCEPTION))
                .bodyToMono(NaverProfile.class)
                .block();

        return new SocialProfile(naverProfile.getResponse().getId(), naverProfile.getResponse().getEmail(), naverProfile.getResponse().getNickname(), naverProfile.getResponse().getProfile_image());
    }
}
