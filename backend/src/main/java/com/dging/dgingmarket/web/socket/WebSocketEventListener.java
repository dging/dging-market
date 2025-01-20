package com.dging.dgingmarket.web.socket;

import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.service.ChatService;
import com.dging.dgingmarket.util.EntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final ChatService chatService;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();

        if(sessionAttributes != null) {
            Long roomId = (Long) sessionAttributes.get("roomId");
            Long userId = (Long) sessionAttributes.get("userId");

            if(roomId != null && userId != null) {
                chatService.leave(roomId, userId);
            }
        }
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        List<String> headerRoomId = headerAccessor.getNativeHeader("room-id");

        Authentication authentication = (Authentication) headerAccessor.getUser();

        if (headerRoomId != null && !headerRoomId.isEmpty() && authentication != null) {
            Long roomId = Long.parseLong(headerRoomId.getFirst());
            User user = (User) authentication.getPrincipal();
            Long userId = user.getId();

            Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
            if (userId != null && sessionAttributes != null) {
                sessionAttributes.put("roomId", roomId);
                sessionAttributes.put("userId", userId);
            }
        }
    }
}
