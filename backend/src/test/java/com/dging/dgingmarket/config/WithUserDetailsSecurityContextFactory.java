package com.dging.dgingmarket.config;

import com.dging.dgingmarket.domain.type.Role;
import com.dging.dgingmarket.domain.store.Store;
import com.dging.dgingmarket.domain.user.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

public class WithUserDetailsSecurityContextFactory implements WithSecurityContextFactory<WithCustomMockUser> {

    @Override
    public SecurityContext createSecurityContext(WithCustomMockUser annotation) {

        User user = User.create("userId", "password", "username", List.of(Role.USER));
        Store store = Store.createEmpty(user);
        ReflectionTestUtils.setField(user, "id", 1L);
        ReflectionTestUtils.setField(store, "id", 1L);
        ReflectionTestUtils.setField(user, "store", store);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(token);
        return context;
    }
}
