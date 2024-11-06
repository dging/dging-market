package com.dging.dgingmarket.domain.store.exception;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.StoreErrorCode;

public class UserOwnStoreException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new UserOwnStoreException();

    public UserOwnStoreException() {
        super(StoreErrorCode.USER_OWN_STORE_ERROR);
    }
}
