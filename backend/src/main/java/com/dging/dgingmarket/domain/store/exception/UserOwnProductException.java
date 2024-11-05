package com.dging.dgingmarket.domain.store.exception;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.StoreErrorCode;

public class UserOwnProductException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new UserOwnProductException();

    public UserOwnProductException() {
        super(StoreErrorCode.USER_OWN_PRODUCT);
    }
}
