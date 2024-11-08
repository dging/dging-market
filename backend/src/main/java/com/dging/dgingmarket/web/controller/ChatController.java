package com.dging.dgingmarket.web.controller;

import com.dging.dgingmarket.dto.ChatMessageDto;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatController {

    @MessageMapping("/chat/{chatRoomId}/send")
    @SendTo("/topic/chat/{chatRoomId}")
    public ChatMessageDto sendMessage(
            @DestinationVariable Long chatRoomId,
            @Payload ChatMessageDto message,
            SimpMessageHeaderAccessor headerAccessor
    ) {

        String sessionId = headerAccessor.getSessionId(); // 세션 ID 가져오기
        System.out.println("Message received from session ID: " + sessionId);

        message.setChatRoomId(chatRoomId);

        return message;
    }
}
