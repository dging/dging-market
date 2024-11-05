package com.dging.dgingmarket.web.api.dto.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    /**
     * COMMON
     */
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST.value(), "CMM-001", "잘못된 입력입니다."),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN.value(), "CMM-002", "접근이 거부되었습니다."),

    /**
     * BUSINESS
     * DGM-1xxx
     */
    LOGIN_FAIL(HttpStatus.BAD_REQUEST.value(), "DGM-1000", "존재하지 않는 계정이거나, 잘못된 비밀번호입니다."),
    ALREADY_SIGNEDUP(HttpStatus.BAD_REQUEST.value(), "DGM-1001", "이미 가입한 사용자입니다."),
    USER_NOT_AUTHENTICATION(HttpStatus.UNAUTHORIZED.value(), "DGM-1002", "인증된 사용자가 아닙니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "DGM-1003", "사용자가 존재하지 않습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "DGM-1004", "리프레시 토큰이 존재하지 않습니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "DGM-1005", "상품이 존재하지 않습니다."),
    USER_OWN_PRODUCT(HttpStatus.FORBIDDEN.value(), "DGM-1006", "사용자의 상품이 아닙니다."),
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "DGM-1007", "이미지가 존재하지 않습니다."),
    FOLLOW_MYSELF_ERROR(HttpStatus.BAD_REQUEST.value(), "DGM-1008", "본인을 팔로우 할 수는 없습니다."),

    /**
     * SOCIAL
     * DGM-2xxx
     */
    SOCIAL_COMMUNICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "DGM-2000", "소셜 인증 과정 중 오류가 발생했습니다."),
    SOCIAL_AGREEMENT_ERROR(HttpStatus.BAD_REQUEST.value(), "DGM-2001", "필수동의 항목에 대해 동의가 필요합니다."),
    INVALID_SOCIAL_TYPE(HttpStatus.BAD_REQUEST.value(), "DGM-2002", "알 수 없는 소셜 타입입니다."),
    SOCIAL_TOKEN_VALID_FAILED(HttpStatus.UNAUTHORIZED.value(), "DGM-2003", "소셜 액세스 토큰 검증에 실패했습니다."),

    /**
     * SECURITY
     * DGM-3xxx
     */
    ACCESS_TOKEN_ERROR(HttpStatus.UNAUTHORIZED.value(), "DGM-3000", "액세스 토큰이 만료되거나 잘못된 값입니다."),
    REFRESH_TOKEN_ERROR(HttpStatus.UNAUTHORIZED.value(), "DGM-3001", "리프레시 토큰이 만료되거나 잘못된 값입니다."),
    TOKEN_PARSE_ERROR(HttpStatus.UNAUTHORIZED.value(), "DGM-3002", "해석할 수 없는 토큰입니다."),

    /**
     * SECURITY
     * DGM-4xxx
     */
    FILE_CONVERT_FAILED(HttpStatus.BAD_REQUEST.value(), "DGM-4000", "파일을 변환할 수 없습니다."),
    INVALID_FILE_FORMAT(HttpStatus.BAD_REQUEST.value(), "DGM-4001", "잘못된 형식의 파일입니다."),
    CLOUD_COMMUNICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "DGM-4002", "파일 업로드 중 오류가 발생했습니다."),
    ;

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}