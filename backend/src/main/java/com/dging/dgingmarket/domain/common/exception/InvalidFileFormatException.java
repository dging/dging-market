package com.dging.dgingmarket.domain.common.exception;

import com.dging.dgingmarket.exception.CommonErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class InvalidFileFormatException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new InvalidFileFormatException();

    public InvalidFileFormatException() {
        super(CommonErrorCode.INVALID_FILE_FORMAT);
    }
}
