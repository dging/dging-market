package com.dging.dgingmarket.exception;

public class SomeFileNotUploadedException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new SomeFileNotUploadedException();

    public SomeFileNotUploadedException() {
        super(CommonErrorCode.SOME_FILE_NOT_UPLOADED);
    }
}
