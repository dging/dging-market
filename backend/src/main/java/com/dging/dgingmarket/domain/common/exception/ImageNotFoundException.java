package com.dging.dgingmarket.domain.common.exception;

import com.dging.dgingmarket.exception.CommonErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class ImageNotFoundException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new ImageNotFoundException();

    public ImageNotFoundException() {
        super(CommonErrorCode.IMAGE_NOT_FOUND);
    }
}
