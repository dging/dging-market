package com.dging.dgingmarket.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

@Getter
public enum StoreErrorCode implements BaseErrorCode {
    /**
     * STORE
     * DGM-3xxx
     */
    FOLLOWER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "DGM-4000", "사용자가 팔로우하거나 사용자를 팔로우한 상점이 아닙니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "DGM-4001", "상점이 존재하지 않습니다."),
    FOLLOW_MYSELF_ERROR(HttpStatus.BAD_REQUEST.value(), "DGM-4100", "본인을 팔로우 할 수는 없습니다."),
    ALREADY_FOLLOWED(HttpStatus.BAD_REQUEST.value(), "DGM-4101", "이미 팔로우한 사용자입니다."),
    ;

    public static final String _FOLLOWER_NOT_FOUND = "DGM-4000";
    public static final String _STORE_NOT_FOUND = "DGM-4001";
    public static final String _FOLLOW_MYSELF_ERROR = "DGM-4100";
    public static final String _ALREADY_FOLLOWED = "DGM-4101";

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

    @Override
    public BaseErrorCode find(String value) throws NoSuchFieldException {
        return Arrays.stream(values()).filter((errorCode) -> errorCode.getCode().equals(value)).findAny().orElse(null);
    }

}