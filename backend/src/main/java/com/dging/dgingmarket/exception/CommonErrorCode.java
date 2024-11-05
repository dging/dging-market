package com.dging.dgingmarket.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

@Getter
public enum CommonErrorCode implements BaseErrorCode {
    /**
     * COMMON
     * DGM-1xxx
     */
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "DGM-1000", "이미지가 존재하지 않습니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST.value(), "DGM-1300", "잘못된 입력입니다."),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN.value(), "DGM-1301", "접근이 거부되었습니다."),
    SOCIAL_COMMUNICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "DGM-1302", "소셜 인증 과정 중 오류가 발생했습니다."),
    SOCIAL_AGREEMENT_ERROR(HttpStatus.BAD_REQUEST.value(), "DGM-1303", "필수동의 항목에 대해 동의가 필요합니다."),
    INVALID_SOCIAL_TYPE(HttpStatus.BAD_REQUEST.value(), "DGM-1304", "알 수 없는 소셜 타입입니다."),
    SOCIAL_TOKEN_VALID_FAILED(HttpStatus.UNAUTHORIZED.value(), "DGM-1305", "소셜 액세스 토큰 검증에 실패했습니다."),
    ACCESS_TOKEN_ERROR(HttpStatus.UNAUTHORIZED.value(), "DGM-1306", "액세스 토큰이 만료되거나 잘못된 값입니다."),
    REFRESH_TOKEN_ERROR(HttpStatus.UNAUTHORIZED.value(), "DGM-1307", "리프레시 토큰이 만료되거나 잘못된 값입니다."),
    TOKEN_PARSE_ERROR(HttpStatus.UNAUTHORIZED.value(), "DGM-1308", "해석할 수 없는 토큰입니다."),
    FILE_CONVERT_FAILED(HttpStatus.BAD_REQUEST.value(), "DGM-1309", "파일을 변환할 수 없습니다."),
    INVALID_FILE_FORMAT(HttpStatus.BAD_REQUEST.value(), "DGM-1310", "잘못된 형식의 파일입니다."),
    CLOUD_COMMUNICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "DGM-1311", "파일 업로드 중 오류가 발생했습니다."),
    SOME_FILE_NOT_UPLOADED(HttpStatus.BAD_REQUEST.value(), "DGM-1312", "업로드가 되지 않은 파일이 존재합니다."),
    SOME_FILE_NOT_YOURS(HttpStatus.BAD_REQUEST.value(), "DGM-1313", "사용자 본인이 업로드하지 않은 파일이 존재합니다."),
    INVALID_FILE(HttpStatus.BAD_REQUEST.value(), "DGM-1314", "파일이 유효하지 않습니다."),
    ;

    public static final String _IMAGE_NOT_FOUND = "DGM-1000";
    public static final String _INVALID_INPUT_VALUE = "DGM-1300";
    public static final String _HANDLE_ACCESS_DENIED = "DGM-1301";
    public static final String _SOCIAL_COMMUNICATION_ERROR = "DGM-1302";
    public static final String _SOCIAL_AGREEMENT_ERROR = "DGM-1303";
    public static final String _INVALID_SOCIAL_TYPE = "DGM-1304";
    public static final String _SOCIAL_TOKEN_VALID_FAILED = "DGM-1305";
    public static final String _ACCESS_TOKEN_ERROR = "DGM-1306";
    public static final String _REFRESH_TOKEN_ERROR = "DGM-1307";
    public static final String _TOKEN_PARSE_ERROR = "DGM-1308";
    public static final String _FILE_CONVERT_FAILED = "DGM-1309";
    public static final String _INVALID_FILE_FORMAT = "DGM-1310";
    public static final String _CLOUD_COMMUNICATION_ERROR = "DGM-1311";
    public static final String _SOME_FILE_NOT_UPLOADED = "DGM-1312";
    public static final String _SOME_FILE_NOT_YOURS = "DGM-1313";
    public static final String _INVALID_FILE = "DGM-1314";

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

    @Override
    public BaseErrorCode find(String value) throws NoSuchFieldException {
        return Arrays.stream(values()).filter((errorCode) -> errorCode.getCode().equals(value)).findAny().orElse(null);
    }
}