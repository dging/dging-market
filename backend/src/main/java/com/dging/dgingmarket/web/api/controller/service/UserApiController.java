package com.dging.dgingmarket.web.api.controller.service;

import com.dging.dgingmarket.client.dto.SocialProfile;
import com.dging.dgingmarket.exception.business.CEntityNotFoundException.CUserNotFoundException;
import com.dging.dgingmarket.service.AuthService;
import com.dging.dgingmarket.service.social.OAuthService;
import com.dging.dgingmarket.util.constant.DocumentDescriptions;
import com.dging.dgingmarket.util.enums.SocialType;
import com.dging.dgingmarket.web.api.dto.common.SocialLoginRequest;
import com.dging.dgingmarket.web.api.dto.common.TokenRequest;
import com.dging.dgingmarket.web.api.dto.common.TokenResponse;
import com.dging.dgingmarket.web.api.dto.product.ProductUpdateRequest;
import com.dging.dgingmarket.web.api.dto.user.LoginRequest;
import com.dging.dgingmarket.web.api.dto.user.SocialSignupRequest;
import com.dging.dgingmarket.web.api.dto.user.UserDetailsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "사용자 서비스", description = "사용자 관리 API 엔드포인트")
@RestController
public class UserApiController {

    private final AuthService authService;
    private final OAuthService oauthService;

/*
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody @Validated SignupRequest signupRequest) {
        signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        authService.signup(signupRequest);
    }
*/

    @PostMapping("/social/{socialType}")
    @Operation(summary = "소셜 회원가입", description = "소셜 계정을 통해 회원가입을 진행합니다.")
    @ApiResponse(responseCode = "201", description = "성공")
    public ResponseEntity<Void> socialSignup(
            @Parameter(description = DocumentDescriptions.REQUEST_SOCIAL_TYPE)
            @PathVariable(name = "socialType") SocialType socialType,
            @RequestBody @Validated
            @Schema(implementation = SocialSignupRequest.class)
            SocialSignupRequest request) {

        //구글은 access_token 대신 id_token 값으로
        SocialProfile socialProfile = oauthService.profile(socialType, request.getAccessToken());

        //소셜 프로필이 없는 경우 에러                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             a
        if (ObjectUtils.isEmpty(socialProfile)) throw new CUserNotFoundException();

/*
        //동의 항목(이메일)에 동의하지 않은 경우 연결 끊은 후 에러
        if (!StringUtils.hasText(socialProfile.getEmail())) {
            throw new CSocialAgreementException();
        }
*/

        authService.socialSignup(socialProfile, socialType);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

/*
    @PostMapping("/token")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse login(@RequestBody @Validated LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
*/

    @PostMapping("/social/{socialType}/token")
    @Operation(summary = "소셜 로그인", description = "소셜 계정을 통해 로그인을 진행합니다.")
    @ApiResponse(responseCode = "201", description = "성공")
    public ResponseEntity<TokenResponse> socialLogin(
            @Parameter(description = DocumentDescriptions.REQUEST_SOCIAL_TYPE)
            @PathVariable(name = "socialType") SocialType socialType,
            @RequestBody @Validated
            @Schema(implementation = SocialLoginRequest.class)
            SocialLoginRequest request
    ) {

        SocialProfile socialProfile = oauthService.profile(socialType, request.getAccessToken());

        //소셜 프로필이 없는 경우 에러
        if (ObjectUtils.isEmpty(socialProfile)) throw new CUserNotFoundException();

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.socialLogin(new LoginRequest(socialProfile.getSnsId(), null, socialType.name().toLowerCase())));
    }

    @PostMapping("/token/expiration")
    @Operation(summary = "액세스 토큰 재발급", description = "리프레시 토큰을 사용하여 액세스 토큰을 재발급합니다.")
    @ApiResponse(responseCode = "201", description = "성공")
    public ResponseEntity<TokenResponse> reissue(
            @RequestBody @Validated
            @Schema(implementation = TokenRequest.class)
            TokenRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.reissue(request));
    }

    @GetMapping("/details")
    @Operation(hidden = true)
    public UserDetailsResponse userDetails() {
        return authService.userDetails();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(hidden = true)
    public void delete(@PathVariable Long id) {
        authService.delete(id);
    }

}
