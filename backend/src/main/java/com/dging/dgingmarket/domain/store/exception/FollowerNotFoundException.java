package com.dging.dgingmarket.domain.store.exception;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.StoreErrorCode;

public class FollowerNotFoundException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new FollowerNotFoundException();

    public FollowerNotFoundException() {
        super(StoreErrorCode.FOLLOWER_NOT_FOUND);
    }
}
