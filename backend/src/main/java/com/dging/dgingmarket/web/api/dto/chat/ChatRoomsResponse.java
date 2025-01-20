package com.dging.dgingmarket.web.api.dto.chat;

import com.dging.dgingmarket.domain.chat.ChatRoom;
import com.dging.dgingmarket.domain.store.Store;
import com.dging.dgingmarket.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

/**
 *  채팅방 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatRoomsResponse {

    @Schema(description = RESPONSE_ID)
    private Long id;

    @Schema(description = RESPONSE_RECIPIENT_ID)
    private Long recipientId;

    @Schema(description = RESPONSE_RECIPIENT_NAME)
    private String recipientName;

    @Schema(description = RESPONSE_CREATED_AT)
    private Date createdAt;

    public static ChatRoomsResponse of(User user, ChatRoom chatRoom) {
        ChatRoomsResponse chatRoomsResponse = new ChatRoomsResponse();
        chatRoomsResponse.setId(chatRoom.getId());

        User recipient = Objects.equals(user.getId(), chatRoom.getFrom().getId()) ? chatRoom.getTo() : chatRoom.getFrom();
        Store recipientStore = recipient.getStore();

        chatRoomsResponse.setRecipientId(recipientStore.getId());
        chatRoomsResponse.setRecipientName(recipientStore.getName());
        chatRoomsResponse.setCreatedAt(chatRoom.getCreatedAt());

        return chatRoomsResponse;
    }
}
