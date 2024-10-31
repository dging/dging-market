package com.dging.dgingmarket.web.api.dto.chat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *  채팅 내역 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatMessagesResponse {

    private Long id;
    private Long senderId;
    private String senderName;
    private boolean isSenderMe;
    private String content;
    private Date sentAt;
}
