package com.dging.dgingmarket.exception;

import com.dging.dgingmarket.web.api.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * @Validated로 검증 시 binding 못하는 경우
     * CustomCollectionValidator로 검증 시 binding 못하는 경우
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(final BindException e) {
        return new ResponseEntity<>(ErrorResponse.of(CommonErrorCode.INVALID_INPUT_VALUE, e.getBindingResult()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 타입이 일치하지 않아 binding 못하는 경우
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException() {
        return new ResponseEntity<>(ErrorResponse.of(CommonErrorCode.INVALID_INPUT_VALUE), HttpStatus.BAD_REQUEST);
    }

    /**
     * BusinessException 하위 클래스
     */
    @ExceptionHandler(DgingMarketException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(final DgingMarketException e) {
        BaseErrorCode code = e.getErrorCode();
        return new ResponseEntity<>(ErrorResponse.of(code), HttpStatus.valueOf(code.getErrorReason().getStatus()));
    }

}
