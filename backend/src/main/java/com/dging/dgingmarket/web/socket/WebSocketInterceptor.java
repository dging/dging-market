package com.dging.dgingmarket.web.socket;

import com.dging.dgingmarket.config.security.JwtProvider;
import com.dging.dgingmarket.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketInterceptor implements ChannelInterceptor {
    private final JwtProvider jwtProvider;

    @SneakyThrows
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (accessor != null) {
            try {
                StompCommand command = accessor.getCommand();
                String token = jwtProvider.resolveToken(accessor);
                if ((command == StompCommand.CONNECT || command == StompCommand.SEND) && token != null && jwtProvider.validationToken(token)) {
                    Authentication auth = jwtProvider.getAuthentication(token);
                    accessor.setUser(auth);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                throw new MessageDeliveryException("Invalid authentication token");
            }
        }

        return message;
    }
}