package com.dging.dgingmarket.web.socket;

import com.dging.dgingmarket.exception.DgingMarketException;
import com.dging.dgingmarket.web.api.dto.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatErrorController {
    private final ObjectMapper objectMapper;

    @MessageExceptionHandler(DgingMarketException.class)
    @SendToUser("/queue/errors")  // 에러 메시지를 특정 사용자에게만 전송
    public String handleDgingMarketException(DgingMarketException e) throws JsonProcessingException {
        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode());
        return objectMapper.writeValueAsString(errorResponse);
    }
}
