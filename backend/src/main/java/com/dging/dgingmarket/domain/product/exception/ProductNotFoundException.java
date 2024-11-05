package com.dging.dgingmarket.domain.product.exception;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.exception.ProductErrorCode;

public class ProductNotFoundException extends DgingMarketException {

    public static final DgingMarketException EXCEPTION = new ProductNotFoundException();

    public ProductNotFoundException() {
        super(ProductErrorCode.PRODUCT_NOT_FOUND);
    }

}
