package com.dging.dgingmarket.listener;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    // 연결이 생성될 때 실행되는 메소드
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String sessionId = headers.getSessionId(); // 세션 ID를 얻음
        System.out.println("New WebSocket connection with session ID: " + sessionId);
        // 여기서 세션 정보를 저장하거나 필요한 로직을 수행할 수 있어.
    }

    // 연결이 종료될 때 실행되는 메소드
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        System.out.println("WebSocket connection closed with session ID: " + sessionId);
        // 여기서 세션 정보를 삭제하거나 필요한 정리 작업을 수행할 수 있어.
    }
}
