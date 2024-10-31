package com.dging.dgingmarket.web.api.dto.chat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *  채팅방 상세 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatRoomResponse {

    private Long chatRoomId;
    private Long recipientId;
    private String recipientName;
    private Date lastRecipientConnectedAt;
    private String productImageUrl;
    private String productTitle;
    private String productPrice;
    private boolean isShippingFreeIncluded;
}
