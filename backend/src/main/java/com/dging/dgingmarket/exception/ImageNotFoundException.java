package com.dging.dgingmarket.exception;

public class ImageNotFoundException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new ImageNotFoundException();

    public ImageNotFoundException() {
        super(CommonErrorCode.IMAGE_NOT_FOUND);
    }
}
