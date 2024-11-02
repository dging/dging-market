package com.dging.dgingmarket.config.security;

import com.dging.dgingmarket.exception.business.CEntityNotFoundException;
import com.dging.dgingmarket.exception.security.CSecurityException;
import com.dging.dgingmarket.web.api.dto.common.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String token = jwtProvider.resolveToken((HttpServletRequest) request);

        log.info("[Verifying token]");
        log.info(((HttpServletRequest) request).getRequestURL().toString());

        try {
            if(StringUtils.hasText(token) && jwtProvider.validationToken(token)) {
                Authentication authentication = jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new CSecurityException.CAuthenticationEntryPointException();
            }
        } catch (CEntityNotFoundException.CUserNotFoundException e) {
            request.setAttribute("exception", ErrorCode.USER_NOT_FOUND.getCode());
        } catch (CSecurityException.CAuthenticationEntryPointException e) {
            request.setAttribute("exception", ErrorCode.ACCESS_TOKEN_ERROR.getCode());
        } finally {
            chain.doFilter(request, response);
        }

    }
}
