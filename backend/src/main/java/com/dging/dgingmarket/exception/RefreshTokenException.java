package com.dging.dgingmarket.exception;

public class RefreshTokenException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new RefreshTokenException();

    public RefreshTokenException() {
        super(CommonErrorCode.REFRESH_TOKEN_ERROR);
    }
}
