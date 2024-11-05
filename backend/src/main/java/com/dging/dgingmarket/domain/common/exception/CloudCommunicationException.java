package com.dging.dgingmarket.domain.common.exception;

import com.dging.dgingmarket.exception.CommonErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class CloudCommunicationException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new CloudCommunicationException();

    public CloudCommunicationException() {
        super(CommonErrorCode.CLOUD_COMMUNICATION_ERROR);
    }
}
