package com.dging.dgingmarket.interceptor;

import com.dging.dgingmarket.config.security.JwtProvider;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketInterceptor implements ChannelInterceptor {

    private final JwtProvider jwtProvider;

    @SneakyThrows
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (accessor.getCommand() == StompCommand.CONNECT) {
            String authToken = accessor.getFirstNativeHeader("Authorization");

            if(authToken != null && authToken.startsWith("Bearer ")) {
                authToken = authToken.split(" ")[1];
            }

            if (authToken == null || !jwtProvider.validationToken(authToken)) {
                throw new AuthException("Authentication failed!!");
            }

            // UsernamePasswordAuthenticationToken 발급
            Authentication authentication = jwtProvider.getAuthentication(authToken);
            // accessor에 등록
            accessor.setUser(authentication);

        }

        return message;
    }
}