package com.dging.dgingmarket.web.controller;

import com.dging.dgingmarket.dto.ChatMessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat/send")
    @SendTo("/topic/chat")
    public ChatMessageDto sendMessage(@Payload ChatMessageDto message, SimpMessageHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId(); // 세션 ID 가져오기
        System.out.println("Message received from session ID: " + sessionId);
        return message;
    }
}
