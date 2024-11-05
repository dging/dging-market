package com.dging.dgingmarket.domain.common.exception;

import com.dging.dgingmarket.exception.CommonErrorCode;
import com.dging.dgingmarket.exception.DgingMarketException;

public class InvalidSocialTypeException extends DgingMarketException {

  public static final DgingMarketException EXCEPTION = new InvalidSocialTypeException();

  public InvalidSocialTypeException() {
    super(CommonErrorCode.INVALID_SOCIAL_TYPE);
  }
}
