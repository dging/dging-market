package com.dging.dgingmarket.web.socket;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.web.api.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.security.Principal;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class WebSocketExceptionHandler {
    private final SimpMessagingTemplate messageTemplate;

    // 비즈니스 로직에 대한 예외 처리
    @MessageExceptionHandler(DgingMarketException.class)
    public void handleBusinessException(Principal principal, DgingMarketException e) {
        ErrorResponse errorMessage = ErrorResponse.of(e.getErrorCode());
        messageTemplate.convertAndSendToUser(principal.getName(), "/queue/errors", errorMessage);
    }
}