package com.dging.dgingmarket.exception;

import com.dging.dgingmarket.documentation.ErrorExplanation;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

@Getter
public enum ChatErrorCode implements BaseErrorCode {
    /**
     * CHAT
     * DGM-5xxx
     */
    CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "DGM-5000", "채팅방이 존재하지 않습니다."),
    ;

    public static final String _CHAT_ROOM_NOT_FOUND = "DGM-5000";

    private int status;
    private final String code;
    private final String reason;

    ChatErrorCode(final int status, final String code, final String reason) {
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