package com.dging.dgingmarket.domain.store.exception;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.StoreErrorCode;

public class StoreNameDuplicatedException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new StoreNameDuplicatedException();

    public StoreNameDuplicatedException() {
        super(StoreErrorCode.STORE_NAME_DUPLICATED);
    }
}
