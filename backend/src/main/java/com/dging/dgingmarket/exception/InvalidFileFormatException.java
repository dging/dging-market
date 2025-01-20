package com.dging.dgingmarket.exception;

public class InvalidFileFormatException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new InvalidFileFormatException();

    public InvalidFileFormatException() {
        super(CommonErrorCode.INVALID_FILE_FORMAT);
    }
}
