package com.dging.dgingmarket.domain.store.exception;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.StoreErrorCode;

public class FollowMyselfException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new FollowMyselfException();

    public FollowMyselfException() {
        super(StoreErrorCode.FOLLOW_MYSELF_ERROR);
    }
}
