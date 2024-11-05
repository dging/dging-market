package com.dging.dgingmarket.domain.user.exception;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.UserErrorCode;

public class UserNotFoundException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new UserNotFoundException();

    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
