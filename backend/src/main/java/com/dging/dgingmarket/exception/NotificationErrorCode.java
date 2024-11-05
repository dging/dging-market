package com.dging.dgingmarket.exception;

import lombok.Getter;

import java.lang.reflect.Field;
import java.util.Objects;

@Getter
public enum NotificationErrorCode implements BaseErrorCode {

    ;

    private int status;
    private final String code;
    private final String reason;

    NotificationErrorCode(final int status, final String code, final String reason) {
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