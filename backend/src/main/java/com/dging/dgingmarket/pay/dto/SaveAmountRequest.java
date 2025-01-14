package com.dging.dgingmarket.pay.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveAmountRequest {
    private String orderId; // 주문 ID
    private String amount;  // 금액 (String 타입으로 처리)
}
