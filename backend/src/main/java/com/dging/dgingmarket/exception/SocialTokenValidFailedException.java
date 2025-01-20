package com.dging.dgingmarket.exception;

public class SocialTokenValidFailedException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new SocialTokenValidFailedException();

    private SocialTokenValidFailedException() {
        super(CommonErrorCode.SOCIAL_TOKEN_VALID_FAILED);
    }
}
