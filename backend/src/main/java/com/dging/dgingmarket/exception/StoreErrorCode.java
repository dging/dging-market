package com.dging.dgingmarket.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.Objects;

@Getter
public enum StoreErrorCode implements BaseErrorCode {
    /**
     * STORE
     * DGM-3xxx
     */
    USER_OWN_PRODUCT(HttpStatus.FORBIDDEN.value(), "DGM-4100", "사용자의 상품이 아닙니다."),
    FOLLOW_MYSELF_ERROR(HttpStatus.BAD_REQUEST.value(), "DGM-4101", "본인을 팔로우 할 수는 없습니다."),
    ;

    private int status;
    private final String code;
    private final String reason;

    StoreErrorCode(final int status, final String code, final String reason) {
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