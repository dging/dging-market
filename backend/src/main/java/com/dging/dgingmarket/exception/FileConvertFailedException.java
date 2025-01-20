package com.dging.dgingmarket.exception;

public class FileConvertFailedException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new FileConvertFailedException();

    public FileConvertFailedException() {
        super(CommonErrorCode.FILE_CONVERT_FAILED);
    }
}
