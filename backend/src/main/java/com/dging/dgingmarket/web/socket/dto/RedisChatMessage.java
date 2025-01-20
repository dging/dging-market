package com.dging.dgingmarket.web.socket.dto;

import com.dging.dgingmarket.domain.chat.ChatMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "chat-message", timeToLive = 10 * 60L)
public class RedisChatMessage {
    @Id
    @Schema(description = RESPONSE_ID)
    private Long id;
    @Schema(description = RESPONSE_MESSAGE_TYPE, example = EXAMPLE_MESSAGE_TYPE)
    private MessageType type;
    @Indexed
    @Schema(description = RESPONSE_CHAT_ROOM_ID)
    private Long roomId;
    @Schema(description = RESPONSE_USER_ID)
    private Long senderId;
    @Schema(description = RESPONSE_MESSAGE_CONTENT)
    private String content;
    @Schema(description = RESPONSE_MESSAGE_READ, example = EXAMPLE_MESSAGE_READ)
    private boolean read;
    @Schema(description = RESPONSE_TIMESTAMP)
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