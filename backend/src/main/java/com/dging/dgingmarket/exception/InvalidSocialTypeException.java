package com.dging.dgingmarket.exception;

public class InvalidSocialTypeException extends DgingMarketException {

  public static final DgingMarketException EXCEPTION = new InvalidSocialTypeException();

  public InvalidSocialTypeException() {
    super(CommonErrorCode.INVALID_SOCIAL_TYPE);
  }
}
