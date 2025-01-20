package com.dging.dgingmarket.exception;

public class AccessDeniedException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new AccessDeniedException();

    public AccessDeniedException() {
        super(CommonErrorCode.HANDLE_ACCESS_DENIED);
    }
}
