package com.dging.dgingmarket.ship.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipUpdateRequest {
    private String trackingNumber;
    private String carrierName;
}
