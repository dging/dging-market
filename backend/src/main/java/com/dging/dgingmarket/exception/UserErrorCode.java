package com.dging.dgingmarket.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.Objects;

@Getter
public enum UserErrorCode implements BaseErrorCode {
    /**
     * USER
     * DGM-2xxx
     */
    @ErrorExplanation("요청을 처리하는 중에 특정 사용자가 존재하지 않을 때 발생하는 에러입니다.")
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "DGM-2000", "사용자가 존재하지 않습니다."),

    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "DGM-2001", "리프레시 토큰이 존재하지 않습니다."),

    LOGIN_FAIL(HttpStatus.BAD_REQUEST.value(), "DGM-2100", "존재하지 않는 계정이거나, 잘못된 비밀번호입니다."),

    ALREADY_SIGNEDUP(HttpStatus.BAD_REQUEST.value(), "DGM-2101", "이미 가입한 사용자입니다."),

    USER_NOT_AUTHENTICATION(HttpStatus.UNAUTHORIZED.value(), "DGM-2102", "인증된 사용자가 아닙니다."),
    ;

    private int status;
    private final String code;
    private final String reason;

    UserErrorCode(final int status, final String code, final String reason) {
        this.status = status;
        this.reason = reason;
        this.code = code;
    }

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(this.reason).code(this.code).status(this.status).build();
    }

    @Override
    public String getErrorExplanation() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ErrorExplanation annotation = field.getAnnotation(ErrorExplanation.class);
        return Objects.nonNull(annotation) ? annotation.value() : this.getReason();
    }
}