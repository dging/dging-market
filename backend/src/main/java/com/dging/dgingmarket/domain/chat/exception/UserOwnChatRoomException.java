package com.dging.dgingmarket.domain.chat.exception;

import com.dging.dgingmarket.exception.ChatErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class UserOwnChatRoomException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new UserOwnChatRoomException();

    public UserOwnChatRoomException() {
        super(ChatErrorCode.USER_OWN_CHAT_ROOM);
    }
}
