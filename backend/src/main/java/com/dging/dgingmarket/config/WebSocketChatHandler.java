package com.dging.dgingmarket.config;

import com.dging.dgingmarket.dto.ChatMessageDto;
import com.dging.dgingmarket.enums.MessageType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper;

    private final Set<WebSocketSession> sessions = new HashSet<>();

    private final Map<Long, Set<WebSocketSession>> chatRoomSessions = new HashMap<>();

    //소켓 연결
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("{} 연결됨", session.getId());
        sessions.add(session);
    }

    //소켓 통신 중 메시지 핸들링
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String payload = message.getPayload();
        log.info("payload {}", payload);

        //payload -> chatMessageDto
        ChatMessageDto chatMessage = mapper.readValue(payload, ChatMessageDto.class);
        log.info("session {}", chatMessage.toString());

        Long chatRoomId = chatMessage.getChatRoomId();
        if(!chatRoomSessions.containsKey(chatRoomId)) {
            chatRoomSessions.put(chatRoomId, new HashSet<>());
        }
        Set<WebSocketSession> chatRoomSession = chatRoomSessions.get(chatRoomId);

        if (chatMessage.getType().equals(MessageType.ENTER)) {
            chatRoomSession.add(session);
        }

        if (chatRoomSession.size() >= 3) {
            removeClosedSession(chatRoomSession);
        }

        sendMessageToChatRoom(chatMessage, chatRoomSession);
    }

    //소켓 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    private void removeClosedSession(Set<WebSocketSession> chatRoomSession) {
        chatRoomSession.removeIf(session -> !sessions.contains(session));
    }

    private void sendMessageToChatRoom(ChatMessageDto chatMessage, Set<WebSocketSession> chatRoomSession) {
        chatRoomSession.parallelStream().forEach(session -> sendMessage(session, chatMessage));
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
