package com.dging.dgingmarket.exception;

public class SocialAgreementException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new InvalidSocialTypeException();

    private SocialAgreementException() {
        super(CommonErrorCode.SOCIAL_AGREEMENT_ERROR);
    }
}
