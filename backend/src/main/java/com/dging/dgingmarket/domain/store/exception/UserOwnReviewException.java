package com.dging.dgingmarket.domain.store.exception;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.StoreErrorCode;

public class UserOwnReviewException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new UserOwnReviewException();

    public UserOwnReviewException() {
        super(StoreErrorCode.USER_OWN_REVIEW_ERROR);
    }
}
