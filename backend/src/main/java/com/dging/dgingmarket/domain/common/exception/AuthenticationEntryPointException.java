package com.dging.dgingmarket.domain.common.exception;

import com.dging.dgingmarket.exception.CommonErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class AuthenticationEntryPointException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new AuthenticationEntryPointException();

    public AuthenticationEntryPointException() {
        super(CommonErrorCode.ACCESS_TOKEN_ERROR);
    }
}
