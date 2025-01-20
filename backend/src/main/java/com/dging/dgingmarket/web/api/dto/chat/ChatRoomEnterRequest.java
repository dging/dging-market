package com.dging.dgingmarket.web.api.dto.chat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomEnterRequest {

    private Long receiverId;
}
