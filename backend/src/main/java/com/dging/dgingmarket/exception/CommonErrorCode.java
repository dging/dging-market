package com.dging.dgingmarket.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.Objects;

@Getter
public enum CommonErrorCode implements BaseErrorCode {
    /**
     * COMMON
     * DGM-1xxx
     */
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "DGM-1000", "이미지가 존재하지 않습니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST.value(), "DGM-1700", "잘못된 입력입니다."),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN.value(), "DGM-1701", "접근이 거부되었습니다."),
    SOCIAL_COMMUNICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "DGM-1702", "소셜 인증 과정 중 오류가 발생했습니다."),
    SOCIAL_AGREEMENT_ERROR(HttpStatus.BAD_REQUEST.value(), "DGM-1703", "필수동의 항목에 대해 동의가 필요합니다."),
    INVALID_SOCIAL_TYPE(HttpStatus.BAD_REQUEST.value(), "DGM-1704", "알 수 없는 소셜 타입입니다."),
    SOCIAL_TOKEN_VALID_FAILED(HttpStatus.UNAUTHORIZED.value(), "DGM-1705", "소셜 액세스 토큰 검증에 실패했습니다."),
    ACCESS_TOKEN_ERROR(HttpStatus.UNAUTHORIZED.value(), "DGM-1706", "액세스 토큰이 만료되거나 잘못된 값입니다."),
    REFRESH_TOKEN_ERROR(HttpStatus.UNAUTHORIZED.value(), "DGM-1707", "리프레시 토큰이 만료되거나 잘못된 값입니다."),
    TOKEN_PARSE_ERROR(HttpStatus.UNAUTHORIZED.value(), "DGM-1708", "해석할 수 없는 토큰입니다."),
    FILE_CONVERT_FAILED(HttpStatus.BAD_REQUEST.value(), "DGM-1709", "파일을 변환할 수 없습니다."),
    INVALID_FILE_FORMAT(HttpStatus.BAD_REQUEST.value(), "DGM-1710", "잘못된 형식의 파일입니다."),
    CLOUD_COMMUNICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "DGM-1711", "파일 업로드 중 오류가 발생했습니다."),
    ;

    private int status;
    private final String code;
    private final String reason;

    CommonErrorCode(final int status, final String code, final String reason) {
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