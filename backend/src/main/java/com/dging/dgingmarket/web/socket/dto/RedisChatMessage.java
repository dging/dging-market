package com.dging.dgingmarket.web.socket.dto;

import com.dging.dgingmarket.domain.chat.ChatMessage;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "chat-message", timeToLive = 10 * 60L)
public class RedisChatMessage {
    @Id
    private Long id;
    private MessageType type;
    @Indexed
    private Long roomId;
    private Long senderId;
    private String content;
    private boolean read;
    private Date timestamp;

    public static RedisChatMessage of(ChatMessage chatMessage, MessageType type) {
        return new RedisChatMessage(
                chatMessage.getId(),
                type,
                chatMessage.getChatRoom().getId(),
                chatMessage.getSender().getId(),
                chatMessage.getContent(),
                chatMessage.isRead(),
                chatMessage.getCreatedAt()
        );
    }
}