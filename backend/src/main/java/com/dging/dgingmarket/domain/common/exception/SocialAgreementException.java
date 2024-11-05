package com.dging.dgingmarket.domain.common.exception;

import com.dging.dgingmarket.exception.CommonErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class SocialAgreementException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new InvalidSocialTypeException();

    private SocialAgreementException() {
        super(CommonErrorCode.SOCIAL_AGREEMENT_ERROR);
    }
}
