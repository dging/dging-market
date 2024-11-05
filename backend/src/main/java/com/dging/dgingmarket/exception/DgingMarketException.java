package com.dging.dgingmarket.exception;

import lombok.Getter;

@Getter
public class DgingMarketException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public DgingMarketException(BaseErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
