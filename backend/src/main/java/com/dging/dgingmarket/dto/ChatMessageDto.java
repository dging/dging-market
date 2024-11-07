package com.dging.dgingmarket.dto;

import com.dging.dgingmarket.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {

    private MessageType type;
    private Long chatRoomId;
    private Long senderId;
    private String message;
}
