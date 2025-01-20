package com.dging.dgingmarket.exception;

public class AuthenticationEntryPointException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new AuthenticationEntryPointException();

    public AuthenticationEntryPointException() {
        super(CommonErrorCode.ACCESS_TOKEN_ERROR);
    }
}
