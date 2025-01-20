package com.dging.dgingmarket.exception;

public class CloudCommunicationException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new CloudCommunicationException();

    public CloudCommunicationException() {
        super(CommonErrorCode.CLOUD_COMMUNICATION_ERROR);
    }
}
