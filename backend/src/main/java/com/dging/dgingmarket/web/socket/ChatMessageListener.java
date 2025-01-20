package com.dging.dgingmarket.web.socket;

import com.dging.dgingmarket.web.socket.dto.RedisChatAccessMessage;
import com.dging.dgingmarket.web.socket.dto.RedisChatMessage;
import com.dging.dgingmarket.web.socket.dto.RedisChatReadMessages;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChatMessageListener implements MessageListener {
    private final ObjectMapper objectMapper;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void onMessage(@NotNull Message message, byte[] pattern) {
        try {

            String channel = new String(message.getChannel());

            switch (channel) {
                case "chat:message":
                    handleChatMessage(message);
                    break;
                case "chat:read":
                    handleChatRead(message);
                    break;
                case "chat:access":
                    handleChatAccess(message);
                    break;
            }
        } catch (Exception e) {
            log.error("Error on Redis Subscribe", e);
        }
    }

    private void handleChatMessage(Message message) throws JsonProcessingException {
        // Redis에서 받은 메시지를 역직렬화
        String publishMessage = new String(message.getBody());
        RedisChatMessage chatMessage = objectMapper.readValue(publishMessage, RedisChatMessage.class);

        // WebSocket subscribers에게 메시지 전달
        messagingTemplate.convertAndSend("/sub/chat-rooms/" + chatMessage.getRoomId() + "/messages", chatMessage);
    }

    private void handleChatRead(Message message) throws JsonProcessingException {
        // Redis에서 받은 메시지를 역직렬화
        String publishMessage = new String(message.getBody());
        RedisChatReadMessages chatReadMessages = objectMapper.readValue(publishMessage, RedisChatReadMessages.class);

        // WebSocket subscribers에게 메시지 전달
        messagingTemplate.convertAndSend("/sub/chat-rooms/" + chatReadMessages.getRoomId() + "/read", chatReadMessages);
    }

    private void handleChatAccess(Message message) throws JsonProcessingException {
        // Redis에서 받은 메시지를 역직렬화
        String publishMessage = new String(message.getBody());
        RedisChatAccessMessage chatReadMessages = objectMapper.readValue(publishMessage, RedisChatAccessMessage.class);

        // WebSocket subscribers에게 메시지 전달
        messagingTemplate.convertAndSend("/sub/chat-rooms/" + chatReadMessages.getRoomId() + "/access", chatReadMessages);
    }
}