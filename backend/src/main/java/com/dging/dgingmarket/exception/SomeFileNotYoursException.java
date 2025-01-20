package com.dging.dgingmarket.exception;

public class SomeFileNotYoursException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new SomeFileNotYoursException();

    public SomeFileNotYoursException() {
        super(CommonErrorCode.SOME_FILE_NOT_YOURS);
    }
}
