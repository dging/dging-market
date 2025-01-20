package com.dging.dgingmarket.domain.chat.exception;

import com.dging.dgingmarket.exception.ChatErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class ChatRoomNotFoundException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new ChatRoomNotFoundException();

    public ChatRoomNotFoundException() {
        super(ChatErrorCode.CHAT_ROOM_NOT_FOUND);
    }
}
