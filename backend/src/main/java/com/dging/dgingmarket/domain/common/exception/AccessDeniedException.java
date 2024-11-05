package com.dging.dgingmarket.domain.common.exception;

import com.dging.dgingmarket.exception.CommonErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class AccessDeniedException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new AccessDeniedException();

    public AccessDeniedException() {
        super(CommonErrorCode.HANDLE_ACCESS_DENIED);
    }
}
