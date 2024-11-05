package com.dging.dgingmarket.domain.user.exception;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.UserErrorCode;

public class AlreadySignedupException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new AlreadySignedupException();

    public AlreadySignedupException() {
        super(UserErrorCode.ALREADY_SIGNEDUP);
    }
}
