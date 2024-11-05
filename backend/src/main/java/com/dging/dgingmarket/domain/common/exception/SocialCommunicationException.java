package com.dging.dgingmarket.domain.common.exception;

import com.dging.dgingmarket.exception.CommonErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class SocialCommunicationException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new SocialCommunicationException();

    public SocialCommunicationException() {
        super(CommonErrorCode.SOCIAL_COMMUNICATION_ERROR);
    }
}
