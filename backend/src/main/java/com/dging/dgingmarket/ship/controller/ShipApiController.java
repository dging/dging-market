package com.dging.dgingmarket.ship.controller;

import com.dging.dgingmarket.ship.dto.*;
import com.dging.dgingmarket.ship.entity.*;
import com.dging.dgingmarket.ship.enums.*;
import com.dging.dgingmarket.ship.service.*;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/ship")
@RequiredArgsConstructor
public class ShipApiController {

    @Value("${tracker.api.client-id}")
    private String clientId;

    private final ShipService shipService;

    /*
        운송장 등록
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerShip(@RequestBody ShipRequest shipRequest) {
        // CarrierType enum에서 carrierName으로 매핑된 값을 찾기
        CarrierType carrier = Arrays.stream(CarrierType.values())
                .filter(c -> c.getName().equals(shipRequest.getCarrierName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 운송업체입니다."));

        // Ship 객체 생성
        Ship newShip = Ship.builder()
                .trackingNumber(shipRequest.getTrackingNumber())
                .carrier(carrier)
                .status(ShippingStatus.IN_PROGRESS) // 초기값은 배송중
                .statusCheckDate(LocalDateTime.now()) // 현재 시간으로 초기화
                .build();

        // ShipService를 사용해 저장
        shipService.registerShip(newShip, shipRequest.getBuyerId(), shipRequest.getStoreId(), shipRequest.getProductId());

        return ResponseEntity.ok("운송장이 성공적으로 등록되었습니다.");
    }

    /*
        운송장 정보 수정
    */
    @PatchMapping("/{shipId}/update")
    public ResponseEntity<String> updateShip(
            @PathVariable Long shipId,
            @RequestBody ShipUpdateRequest updateRequest) {

        CarrierType carrier = Arrays.stream(CarrierType.values())
                .filter(c -> c.getName().equals(updateRequest.getCarrierName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 택배사 이름입니다."));

        shipService.updateShip(shipId, updateRequest.getTrackingNumber(), carrier);

        return ResponseEntity.ok("운송장 정보가 성공적으로 수정되었습니다.");
    }


    /*
        운송장 조회 링크 생성
    */
    @GetMapping("/{shipId}/generate-link")
    public ResponseEntity<String> generateTrackingLink(@PathVariable Long shipId) {
        Ship ship = shipService.getShipById(shipId);

        String trackingInfoJson = String.format(
                "{\"clientId\":\"%s\", \"carrierId\":\"%s\", \"trackingNumber\":\"%s\"}",
                clientId, // 이 값은 서비스에서 관리되는 안전한 값이어야 합니다.
                ship.getCarrier().getCode(),
                ship.getTrackingNumber()
        );

        String encodedInfo = Base64.getEncoder().encodeToString(trackingInfoJson.getBytes());

        String link = String.format("https://tracker.delivery/carriers/%s#info=%s",
                ship.getCarrier().getCode(), encodedInfo);

        return ResponseEntity.ok(link);
    }

    // 구매자 내역: 구매자가 관련된 운송장 정보 가져오기
    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<Map<String, Object>> getShipmentsByBuyer(@PathVariable Long buyerId) {
        List<ShipResponseForBuyer> shipments = shipService.getShipmentsByBuyerId(buyerId);
        Map<String, Object> response = new HashMap<>();
        response.put("buyerId", buyerId);
        response.put("shipments", shipments);
        return ResponseEntity.ok(response);
    }

    // 판매자 내역: 판매자가 관련된 운송장 정보 가져오기
    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<Map<String, Object>> getShipmentsBySeller(@PathVariable Long sellerId) {
        List<ShipResponseForSeller> shipments = shipService.getShipmentsBySellerId(sellerId);
        Map<String, Object> response = new HashMap<>();
        response.put("sellerId", sellerId);
        response.put("shipments", shipments);
        return ResponseEntity.ok(response);
    }



}


