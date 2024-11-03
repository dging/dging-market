package com.dging.dgingmarket.config;

import com.dging.dgingmarket.domain.common.enums.Role;
import com.dging.dgingmarket.domain.user.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;

public class WithUserDetailsSecurityContextFactory implements WithSecurityContextFactory<WithCustomMockUser> {

    @Override
    public SecurityContext createSecurityContext(WithCustomMockUser annotation) {

        User user = User.createMock(1L, "password", List.of(Role.USER));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(token);
        return context;
    }
}
