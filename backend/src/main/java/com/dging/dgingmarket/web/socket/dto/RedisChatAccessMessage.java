package com.dging.dgingmarket.web.socket.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RedisChatAccessMessage {
    private Long roomId;
    private Long storeId;
    private Date lastConnectedAt;
    private boolean currentConnected;

    public static RedisChatAccessMessage create(Long roomId, Long recipientId, Date lastRecipientConnectedAt, boolean currentRecipientConnected) {
        return new RedisChatAccessMessage(roomId, recipientId, lastRecipientConnectedAt, currentRecipientConnected);
    }
}
