package com.dging.dgingmarket.web.socket;

import com.dging.dgingmarket.service.ChatService;
import com.dging.dgingmarket.web.socket.dto.RedisChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/message")
    public void message(RedisChatMessage message) {

        switch (message.getType()) {
            case JOIN -> handleJoinMessage(message);
            case TALK -> handleChatMessage(message);
        }
    }

    private void handleJoinMessage(RedisChatMessage message) {
        chatService.join(message);
    }

    private void handleChatMessage(RedisChatMessage message) {
        // 일반 채팅 메시지 처리
        chatService.sendMessage(message);
    }
}