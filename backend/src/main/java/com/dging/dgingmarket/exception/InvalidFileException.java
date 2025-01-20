package com.dging.dgingmarket.exception;

public class InvalidFileException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new InvalidFileException();

    public InvalidFileException() {
        super(CommonErrorCode.INVALID_FILE);
    }
}
