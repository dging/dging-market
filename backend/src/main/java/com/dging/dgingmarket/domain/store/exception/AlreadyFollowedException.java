package com.dging.dgingmarket.domain.store.exception;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.StoreErrorCode;

public class AlreadyFollowedException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new AlreadyFollowedException();

    public AlreadyFollowedException() {
        super(StoreErrorCode.ALREADY_FOLLOWED);
    }
}
