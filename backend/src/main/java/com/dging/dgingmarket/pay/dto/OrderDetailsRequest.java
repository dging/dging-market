package com.dging.dgingmarket.pay.dto;

import lombok.Data;

@Data
public class OrderDetailsRequest {
    private Long buyerId;
    private Long storeId;
    private Long productId;
}
