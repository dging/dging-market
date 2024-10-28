package com.dging.dgingmarket.client;

import com.dging.dgingmarket.DgingMarketApplication;
import com.dging.dgingmarket.service.UrlService;
import com.google.gson.Gson;
import com.dging.dgingmarket.client.dto.GoogleProfile;
import com.dging.dgingmarket.client.dto.OAuthTokenResponse;
import com.dging.dgingmarket.client.dto.SocialProfile;
import com.dging.dgingmarket.exception.social.CSocialException.CSocialCommunicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@Qualifier(value = "dgingmarket")
@RequiredArgsConstructor
public class GoogleOAuthClient implements OAuthClient {

    private final RestTemplate restTemplate;
    private final Gson gson;
    private final WebClient webClient;
    private final DgingMarketApplication application;

    private final UrlService urlService;

    @Value("${dging-market.social.google.client-id}")
    private String googleClientId;

    @Value("${dging-market.social.google.client-secret}")
    private String googleClientSecret;

    @Value("${dging-market.social.google.redirect}")
    private String googleRedirectUri;

    @Value("${dging-market.social.google.url.login}")
    private String googleLoginUrl;

    @Value("${dging-market.social.google.url.token}")
    private String googleTokenUrl;

    @Value("${dging-market.social.google.url.profile}")
    private String googleProfileUrl;

    @Override
    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();
        //카카오, 네이버를 제외하고는 parameter에 scope를 지정해줘야 동의됨
        params.put("scope", "profile email");
        params.put("response_type", "code");
        params.put("client_id", googleClientId);
        params.put("redirect_uri", urlService.getServerBaseUrl() + googleRedirectUri);

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        return googleLoginUrl + "?" + parameterString;
    }

    @Override
    public OAuthTokenResponse getTokenInfo(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("access_type", "offline");
        params.add("grant_type", "authorization_code");
        params.add("client_id", googleClientId);
        params.add("client_secret", googleClientSecret);
        params.add("redirect_uri", urlService.getServerBaseUrl() + googleRedirectUri);
        params.add("code", code);

        try {
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(googleTokenUrl, request, String.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return gson.fromJson(responseEntity.getBody(), OAuthTokenResponse.class);
            }
        } catch (HttpClientErrorException e) {
            log.error("e={}", e.toString());
        }
        throw new CSocialCommunicationException();
    }

    @Override
    public SocialProfile getProfile(String idToken) {

        GoogleProfile googleProfile = webClient.get()
                .uri(googleProfileUrl, builder -> builder.queryParam("id_token", idToken).build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new CSocialCommunicationException()))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new CSocialCommunicationException()))
                .bodyToMono(GoogleProfile.class)
                .block();

        return new SocialProfile(googleProfile.getSub()+googleProfile.getAzp(), googleProfile.getEmail(), googleProfile.getName(), googleProfile.getPicture());
    }
}
