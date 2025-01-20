package com.dging.dgingmarket.web.socket.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RedisChatReadMessages {
    private Long roomId;
    private List<RedisChatMessage> messages;

    public static RedisChatReadMessages create(Long roomId, List<RedisChatMessage> messages) {
        return new RedisChatReadMessages(roomId, messages);
    }
}
