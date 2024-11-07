package com.dging.dgingmarket.domain.store.exception;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.StoreErrorCode;

public class ReviewMyselfException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new ReviewMyselfException();

    public ReviewMyselfException() {
        super(StoreErrorCode.REVIEW_MYSELF_ERROR);
    }
}
