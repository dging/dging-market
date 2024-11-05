package com.dging.dgingmarket.domain.common.exception;

import com.dging.dgingmarket.exception.CommonErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class FileConvertFailedException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new FileConvertFailedException();

    public FileConvertFailedException() {
        super(CommonErrorCode.FILE_CONVERT_FAILED);
    }
}
