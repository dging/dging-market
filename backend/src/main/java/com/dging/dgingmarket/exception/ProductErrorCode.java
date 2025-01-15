package com.dging.dgingmarket.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

@Getter
public enum ProductErrorCode implements BaseErrorCode {
    /**
     * PRODUCT
     * DGM-2xxx
     */
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "DGM-3000", "상품이 존재하지 않습니다."),
    USER_OWN_PRODUCT(HttpStatus.FORBIDDEN.value(), "DGM-3100", "사용자 본인의 상품이 아닙니다."),
    ;

    public static final String _PRODUCT_NOT_FOUND = "DGM-3000";
    public static final String _USER_OWN_PRODUCT = "DGM-3100";

    private int status;
    private final String code;
    private final String reason;

    ProductErrorCode(final int status, final String code, final String reason) {
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

    @Override
    public BaseErrorCode find(String value) throws NoSuchFieldException {
        return Arrays.stream(values()).filter((errorCode) -> errorCode.getCode().equals(value)).findAny().orElse(null);
    }
}