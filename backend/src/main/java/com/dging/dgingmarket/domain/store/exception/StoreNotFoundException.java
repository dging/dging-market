package com.dging.dgingmarket.domain.store.exception;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.StoreErrorCode;

public class StoreNotFoundException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new StoreNotFoundException();

    public StoreNotFoundException() {
        super(StoreErrorCode.STORE_NOT_FOUND);
    }
}
