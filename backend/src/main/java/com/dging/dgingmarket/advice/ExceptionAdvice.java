package com.dging.dgingmarket.advice;

import com.dging.dgingmarket.web.api.dto.common.ErrorCode;
import com.dging.dgingmarket.web.api.dto.common.ErrorResponse;
import com.dging.dgingmarket.exception.business.CBusinessException;
import com.dging.dgingmarket.exception.io.CIOException;
import com.dging.dgingmarket.exception.security.CSecurityException;
import com.dging.dgingmarket.exception.social.CSocialException;
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
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final BindException e) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 타입이 일치하지 않아 binding 못하는 경우
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException() {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE), HttpStatus.BAD_REQUEST);
    }

    /**
     * CIOException 하위 클래스
     */
    @ExceptionHandler(CIOException.class)
    public ResponseEntity<ErrorResponse> handleCIOException(final CIOException e) {
        return new ResponseEntity<>(ErrorResponse.of(e.getErrorCode()), HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    /**
     * CSecurityException 하위 클래스
     *
     * * TokenException
     * * * CAuthenticationEntryPointException: Jwt가 없거나 잘못된 경우
     * * * CAccessDeniedException: 리소스에 접근할 권한이 없는 경우
     */
    @ExceptionHandler(CSecurityException.class)
    public ResponseEntity<ErrorResponse> handleCSecurityException(final CSecurityException e) {
        return new ResponseEntity<>(ErrorResponse.of(e.getErrorCode()), HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    /**
     * CSocialException 하위 클래스
     */
    @ExceptionHandler(CSocialException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(final CSocialException e) {
        return new ResponseEntity<>(ErrorResponse.of(e.getErrorCode()), HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }


    /**
     * BusinessException 하위 클래스
     */
    @ExceptionHandler(CBusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(final CBusinessException e) {
        return new ResponseEntity<>(ErrorResponse.of(e.getErrorCode()), HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

}
