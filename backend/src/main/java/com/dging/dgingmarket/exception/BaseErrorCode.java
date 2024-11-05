package com.dging.dgingmarket.exception;

public interface BaseErrorCode {

    ErrorReason getErrorReason();
    String getErrorExplanation() throws NoSuchFieldException;
    BaseErrorCode find(String code) throws NoSuchFieldException;
}
