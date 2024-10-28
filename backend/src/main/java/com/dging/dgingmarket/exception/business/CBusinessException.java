package com.dging.dgingmarket.exception.business;

import com.dging.dgingmarket.web.api.dto.common.ErrorCode;
import lombok.Getter;

@Getter
public class CBusinessException extends RuntimeException {

    private ErrorCode errorCode;

    public CBusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
