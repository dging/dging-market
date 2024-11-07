package com.dging.dgingmarket.domain.store.exception;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.StoreErrorCode;

public class ReviewNotFoundException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new ReviewNotFoundException();

    public ReviewNotFoundException() {
        super(StoreErrorCode.REVIEW_NOT_FOUND);
    }
}
