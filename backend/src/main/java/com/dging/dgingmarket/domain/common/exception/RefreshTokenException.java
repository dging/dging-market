package com.dging.dgingmarket.domain.common.exception;

import com.dging.dgingmarket.exception.CommonErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class RefreshTokenException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new RefreshTokenException();

    public RefreshTokenException() {
        super(CommonErrorCode.REFRESH_TOKEN_ERROR);
    }
}
