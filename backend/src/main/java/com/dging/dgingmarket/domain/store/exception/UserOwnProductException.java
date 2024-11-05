package com.dging.dgingmarket.domain.store.exception;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.ProductErrorCode;

public class UserOwnProductException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new UserOwnProductException();

    public UserOwnProductException() {
        super(ProductErrorCode.USER_OWN_PRODUCT);
    }
}
