package com.dging.dgingmarket.domain.user.exception;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.UserErrorCode;

public class LoginFailedException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new LoginFailedException();

    public LoginFailedException() {
        super(UserErrorCode.LOGIN_FAIL);
    }
}
