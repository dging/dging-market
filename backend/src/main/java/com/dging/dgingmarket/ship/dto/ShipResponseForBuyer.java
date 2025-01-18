package com.dging.dgingmarket.ship.dto;

import com.dging.dgingmarket.ship.enums.ShippingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShipResponseForBuyer {
    private Long shipId;
    private Long productId;
    private Long sellerId;
    private String trackingNumber;
    private String carrierName;
    private String carrierCode;
    private ShippingStatus status;
    private LocalDateTime statusCheckDate;
}
