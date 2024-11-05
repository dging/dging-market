package com.dging.dgingmarket.service;

import com.dging.dgingmarket.client.dto.SocialProfile;
import com.dging.dgingmarket.config.security.JwtProvider;
import com.dging.dgingmarket.domain.common.enums.Role;
import com.dging.dgingmarket.domain.refreshtoken.RefreshToken;
import com.dging.dgingmarket.domain.refreshtoken.RefreshTokenRepository;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.domain.user.exception.AlreadySignedupException;
import com.dging.dgingmarket.domain.user.exception.UserNotFoundException;
import com.dging.dgingmarket.domain.common.exception.RefreshTokenException;
import com.dging.dgingmarket.util.EntityUtils;
import com.dging.dgingmarket.util.enums.SocialType;
import com.dging.dgingmarket.web.api.dto.common.TokenRequest;
import com.dging.dgingmarket.web.api.dto.common.TokenResponse;
import com.dging.dgingmarket.web.api.dto.user.LoginRequest;
import com.dging.dgingmarket.web.api.dto.user.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void socialSignup(SocialProfile socialProfile, SocialType socialType) {

        userRepository.findBySocialIdAndProvider(socialProfile.getSnsId(), socialType.name().toLowerCase())
                .ifPresent(user -> {
                    throw AlreadySignedupException.EXCEPTION;
                });

        userRepository.save(
                User.createSocial(
                        passwordEncoder.encode(UUID.randomUUID().toString()),
                        socialType.name().toLowerCase(),
                        socialProfile.getSnsId(),
                        socialProfile.getUsername(),
                        socialProfile.getProfileImageUrl()
                )
        );

    }

    @Transactional
    public TokenResponse socialLogin(LoginRequest request) {
        User user = userRepository.findBySocialIdAndProvider(request.getId(), request.getProvider())
                .orElseThrow(UserNotFoundException::new);
        return socialLogin(user);
    }

    private TokenResponse socialLogin(User user) {
        refreshTokenRepository.findByKey(user.getId()).ifPresent(refreshTokenRepository::delete);
        TokenResponse tokenResponse = jwtProvider.createToken(user.getId().toString(), user.getRoles().stream().map(Role::getValue).collect(Collectors.toList()));
        refreshTokenRepository.save(RefreshToken.create(user.getId(), tokenResponse.getRefreshToken()));
        return tokenResponse;
    }

    /**
     * TokenRequest를 통해 액세스 토큰 재발급 요청
     * * 리프레시 토큰 만료 검증 -> 만료 시 재로그인 요청
     */
    @Transactional
    public TokenResponse reissue(TokenRequest request) {

        //리프레시 토큰 만료
        if(!jwtProvider.validationToken(request.getRefreshToken())) {
            throw RefreshTokenException.EXCEPTION;
        }

        String accessToken = request.getAccessToken();
        Authentication authentication = jwtProvider.getAuthentication(accessToken);
        User foundUser = EntityUtils.userThrowable(userRepository, ((User)authentication.getPrincipal()).getId());

        //리프레시 토큰 없음
        RefreshToken refreshToken = refreshTokenRepository.findByKey(foundUser.getId())
                .orElseThrow(RefreshTokenException::new);

        //리프레시 토큰 불일치
        if(!refreshToken.getToken().equals(request.getRefreshToken())) {
            throw RefreshTokenException.EXCEPTION;
        }

        TokenResponse newCreatedToken = jwtProvider.createToken(foundUser.getId().toString(), foundUser.getRoles().stream().map(Role::getValue).collect(Collectors.toList()));
        refreshToken.update(newCreatedToken.getRefreshToken());

        log.debug(newCreatedToken.getAccessToken());

        return newCreatedToken;
    }

    public UserDetailsResponse userDetails() {
        User user = EntityUtils.userThrowable();
        return new UserDetailsResponse(
                user.getId(),
                user.getUserId(),
                user.getUsername(),
                user.getProvider(),
                user.getCreatedAt()
        );
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
        refreshTokenRepository.deleteByKey(id);
    }
}
