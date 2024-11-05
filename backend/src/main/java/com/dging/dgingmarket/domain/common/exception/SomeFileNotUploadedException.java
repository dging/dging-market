package com.dging.dgingmarket.domain.common.exception;

import com.dging.dgingmarket.exception.CommonErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class SomeFileNotUploadedException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new SomeFileNotUploadedException();

    public SomeFileNotUploadedException() {
        super(CommonErrorCode.SOME_FILE_NOT_UPLOADED);
    }
}
