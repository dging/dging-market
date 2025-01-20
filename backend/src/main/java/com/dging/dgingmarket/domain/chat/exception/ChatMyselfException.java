package com.dging.dgingmarket.domain.chat.exception;

import com.dging.dgingmarket.exception.ChatErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.StoreErrorCode;

public class ChatMyselfException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new ChatMyselfException();

    public ChatMyselfException() {
        super(ChatErrorCode.CHAT_MYSELF_ERROR);
    }
}
