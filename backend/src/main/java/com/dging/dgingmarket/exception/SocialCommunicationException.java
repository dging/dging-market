package com.dging.dgingmarket.exception;

public class SocialCommunicationException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new SocialCommunicationException();

    public SocialCommunicationException() {
        super(CommonErrorCode.SOCIAL_COMMUNICATION_ERROR);
    }
}
