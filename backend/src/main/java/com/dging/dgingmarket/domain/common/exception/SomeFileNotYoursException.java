package com.dging.dgingmarket.domain.common.exception;

import com.dging.dgingmarket.exception.CommonErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class SomeFileNotYoursException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new SomeFileNotYoursException();

    public SomeFileNotYoursException() {
        super(CommonErrorCode.SOME_FILE_NOT_YOURS);
    }
}
