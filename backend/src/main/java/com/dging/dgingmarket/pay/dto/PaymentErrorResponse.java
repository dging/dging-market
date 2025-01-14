package com.dging.dgingmarket.pay.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentErrorResponse {
    private int code;  // 오류 코드
    private String message;  // 오류 메시지
}
