package com.dging.dgingmarket.web.api.controller.service;

import com.dging.dgingmarket.documentation.ApiErrorCodeClassExample;
import com.dging.dgingmarket.exception.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/examples")
@RequiredArgsConstructor
@Tag(name = "에러코드 예시")
@RestController
public class ExampleApiController {

    @GetMapping("/global")
    @Operation(summary = "글로벌(인증, 서버 내부 에러 등) 에러코드")
    @ApiErrorCodeClassExample(CommonErrorCode.class)
    public void globalErrorCodes() {}

    @GetMapping("/user")
    @Operation(summary = "사용자 도메인 관련 에러코드")
    @ApiErrorCodeClassExample(UserErrorCode.class)
    public void userErrorCodes() {}

    @GetMapping("/product")
    @Operation(summary = "상품 도메인 관련 에러코드")
    @ApiErrorCodeClassExample(ProductErrorCode.class)
    public void productErrorCodes() {}

    @GetMapping("/store")
    @Operation(summary = "상점 도메인 관련 에러코드")
    @ApiErrorCodeClassExample(StoreErrorCode.class)
    public void storeErrorCodes() {}

    @GetMapping("/notification")
    @Operation(summary = "알림 도메인 관련 에러코드")
    @ApiErrorCodeClassExample(NotificationErrorCode.class)
    public void notificationErrorCodes() {}

    @GetMapping("/chat")
    @Operation(summary = "채팅 도메인 관련 에러코드")
    @ApiErrorCodeClassExample(ChatErrorCode.class)
    public void chatErrorCodes() {}
}
