package com.dging.dgingmarket.domain.common.exception;

import com.dging.dgingmarket.exception.CommonErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class InvalidFileException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new InvalidFileException();

    public InvalidFileException() {
        super(CommonErrorCode.INVALID_FILE);
    }
}
