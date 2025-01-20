package com.dging.dgingmarket.web.api.dto.chat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomQuitRequest {

    private Long receiverId;
    private Long chatRoomId;
}
