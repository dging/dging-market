package com.dging.dgingmarket.web.socket;

import com.dging.dgingmarket.service.ChatService;
import com.dging.dgingmarket.web.socket.dto.RedisChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/message")
    public void message(RedisChatMessage message) {

        switch (message.getType()) {
            case JOIN -> handleJoinMessage(message);
            case TALK -> handleChatMessage(message);
        }
    }

    @ResponseBody
    @GetMapping("/chat-rooms/{roomId}/messages")
    public ResponseEntity<List<RedisChatMessage>> fetchChatMessages(@PathVariable Long roomId) {
        List<RedisChatMessage> messages = chatService.chatMessages(roomId);
        return ResponseEntity.ok(messages);
    }

    private void handleJoinMessage(RedisChatMessage message) {
        chatService.join(message);
    }

    private void handleChatMessage(RedisChatMessage message) {
        // 일반 채팅 메시지 처리
        chatService.sendMessage(message);
    }
}