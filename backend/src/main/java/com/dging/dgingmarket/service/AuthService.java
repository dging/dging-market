package com.dging.dgingmarket.service;

import com.dging.dgingmarket.client.dto.SocialProfile;
import com.dging.dgingmarket.config.security.JwtProvider;
import com.dging.dgingmarket.domain.common.enums.Role;
import com.dging.dgingmarket.domain.refreshtoken.RefreshToken;
import com.dging.dgingmarket.domain.refreshtoken.RefreshTokenRepository;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.exception.business.CInvalidValueException.CAlreadySignedupException;
import com.dging.dgingmarket.exception.security.CTokenException;
import com.dging.dgingmarket.util.EntityUtils;
import com.dging.dgingmarket.util.enums.SocialType;
import com.dging.dgingmarket.web.api.dto.common.TokenRequest;
import com.dging.dgingmarket.web.api.dto.common.TokenResponse;
import com.dging.dgingmarket.web.api.dto.user.LoginRequest;
import com.dging.dgingmarket.web.api.dto.user.SocialSignupRequest;
import com.dging.dgingmarket.web.api.dto.user.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

import static com.dging.dgingmarket.exception.business.CEntityNotFoundException.CUserNotFoundException;

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
    public void socialSignup(SocialProfile socialProfile, SocialType socialType, SocialSignupRequest request) {

        userRepository.findBySocialIdAndProvider(socialProfile.getSnsId(), socialType.name().toLowerCase())
                .ifPresent(user -> {
                    throw new CAlreadySignedupException();
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
                .orElseThrow(CUserNotFoundException::new);
        return socialLogin(user);
    }

    @Transactional
    public TokenResponse socialLogin(User user) {
        refreshTokenRepository.findByKey(user.getSeq().toString()).ifPresent(refreshTokenRepository::delete);
        TokenResponse tokenResponse = jwtProvider.createToken(user.getSeq().toString(), user.getRoles().stream().map(Role::getValue).collect(Collectors.toList()));
        refreshTokenRepository.save(RefreshToken.create(user.getSeq().toString(), tokenResponse.getRefreshToken()));
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
            throw new CTokenException.CRefreshTokenException();
        }

        String accessToken = request.getAccessToken();
        Authentication authentication = jwtProvider.getAuthentication(accessToken);
        User foundUser = EntityUtils.userThrowable(userRepository, ((User)authentication.getPrincipal()).getSeq());

        //리프레시 토큰 없음
        RefreshToken refreshToken = refreshTokenRepository.findByKey(foundUser.getSeq().toString())
                .orElseThrow(CTokenException.CRefreshTokenException::new);

        //리프레시 토큰 불일치
        if(!refreshToken.getToken().equals(request.getRefreshToken())) {
            throw new CTokenException.CRefreshTokenException();
        }

        TokenResponse newCreatedToken = jwtProvider.createToken(foundUser.getSeq().toString(), foundUser.getRoles().stream().map(Role::getValue).collect(Collectors.toList()));
        refreshToken.update(newCreatedToken.getRefreshToken());

        log.debug(newCreatedToken.getAccessToken());

        return newCreatedToken;
    }

    public UserDetailsResponse userDetails() {
        User user = EntityUtils.userThrowable();
        return new UserDetailsResponse(
                user.getSeq().toString(),
                user.getUserId(),
                user.getUsername(),
                user.getProvider(),
                user.getCreatedAt()
        );
    }

    @Transactional
    public void delete(Integer userSeq) {
        userRepository.deleteById(userSeq);
        refreshTokenRepository.deleteByKey(userSeq.toString());
    }
}
