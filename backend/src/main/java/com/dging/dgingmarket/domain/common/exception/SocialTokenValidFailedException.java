package com.dging.dgingmarket.domain.common.exception;

import com.dging.dgingmarket.exception.CommonErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class SocialTokenValidFailedException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new SocialTokenValidFailedException();

    private SocialTokenValidFailedException() {
        super(CommonErrorCode.SOCIAL_TOKEN_VALID_FAILED);
    }
}
