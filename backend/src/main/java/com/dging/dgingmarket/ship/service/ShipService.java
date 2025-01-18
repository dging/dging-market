package com.dging.dgingmarket.ship.service;

import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.product.ProductRepository;
import com.dging.dgingmarket.domain.store.Store;
import com.dging.dgingmarket.domain.store.StoreRepository;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.ship.entity.Ship;
import com.dging.dgingmarket.ship.dto.*;
import com.dging.dgingmarket.ship.enums.*;
import com.dging.dgingmarket.ship.repository.ShipRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShipService {
    @Value("${tracker.api.client-id}")
    private String clientId;

    @Value("${tracker.api.client-secret}")
    private String clientSecret;

    private final ShipRepository shipRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    // 운송장 등록
    public void registerShip(Ship ship, Long buyerId, Long storeId, Long productId) {

        if (shipRepository.existsByProduct_Id(productId)) {
            throw new IllegalArgumentException("이미 등록된 상품입니다: productId=" + productId);
        }

        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("구매자를 찾을 수 없습니다. buyerId=" + buyerId));
        Store seller = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("판매자를 찾을 수 없습니다. storeId=" + storeId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. productId=" + productId));

        ship.setBuyer(buyer);
        ship.setSeller(seller);
        ship.setProduct(product);

        shipRepository.save(ship);
    }



    // 기존 운송장 정보 수정
    public void updateShip(Long shipId, String trackingNumber, CarrierType carrier) {
        Ship ship = shipRepository.findById(shipId)
                .orElseThrow(() -> new IllegalArgumentException("해당 운송장을 찾을 수 없습니다."));

        ship.setTrackingNumber(trackingNumber);
        ship.setCarrier(carrier);

        shipRepository.save(ship);
    }


    // 구매자 ID로 운송장 정보 조회 및 상태 업데이트
    public List<ShipResponseForBuyer> getShipmentsByBuyerId(Long buyerId) {
        List<Ship> shipments = shipRepository.findByBuyer_Id(buyerId);

        shipments.forEach(ship -> {
            checkAndUpdateShipment(ship.getCarrier().getCode(), ship.getTrackingNumber());
        });

        return shipments.stream()
                .map(ship -> ShipResponseForBuyer.builder()
                        .shipId(ship.getShipId())
                        .sellerId(ship.getSeller().getId())
                        .productId(ship.getProduct().getId())
                        .trackingNumber(ship.getTrackingNumber())
                        .carrierName(ship.getCarrier().getName())
                        .carrierCode(ship.getCarrier().getCode())
                        .status(ship.getStatus())
                        .statusCheckDate(ship.getStatusCheckDate())
                        .build())
                .collect(Collectors.toList());
    }


    // 판매자 ID로 운송장 정보 조회 및 상태 업데이트
    public List<ShipResponseForSeller> getShipmentsBySellerId(Long sellerId) {
        List<Ship> shipments = shipRepository.findBySeller_Id(sellerId);

        shipments.forEach(ship -> {
            checkAndUpdateShipment(ship.getCarrier().getCode(), ship.getTrackingNumber());
        });

        return shipments.stream()
                .map(ship -> ShipResponseForSeller.builder()
                        .shipId(ship.getShipId())
                        .buyerId(ship.getBuyer().getId())
                        .productId(ship.getProduct().getId())
                        .trackingNumber(ship.getTrackingNumber())
                        .carrierName(ship.getCarrier().getName())
                        .carrierCode(ship.getCarrier().getCode())
                        .status(ship.getStatus())
                        .statusCheckDate(ship.getStatusCheckDate())
                        .build())
                .collect(Collectors.toList());
    }


    // 운송장 상태 확인 및 업데이트
    public ShipResponse checkAndUpdateShipment(String carrierCode, String trackingNumber) {
        CarrierType carrierType = CarrierType.fromCode(carrierCode);

        Ship ship = shipRepository.findByCarrierAndTrackingNumber(carrierType, trackingNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 운송장을 찾을 수 없습니다."));

        if (ship.getStatus() == ShippingStatus.DELIVERED) {
            return convertToDto(ship);
        }

        LocalDateTime now = LocalDateTime.now();
        if (ship.getStatusCheckDate() != null && Duration.between(ship.getStatusCheckDate(), now).toHours() < 10) {
            return convertToDto(ship);
        }

        String apiUrl = "https://apis.tracker.delivery/graphql";
        String query = """
        query Track($carrierId: ID!, $trackingNumber: String!) {
          track(carrierId: $carrierId, trackingNumber: $trackingNumber) {
            lastEvent {
              time
              status {
                code
              }
            }
          }
        }
        """;

        Map<String, Object> variables = Map.of(
                "carrierId", ship.getCarrier().getCode(),
                "trackingNumber", trackingNumber
        );

        Map<String, Object> requestBody = Map.of(
                "query", query,
                "variables", variables
        );

        HttpHeaders headers = new HttpHeaders();
        String apiKey = clientId + ":" + clientSecret;
        headers.set("Authorization", "TRACKQL-API-KEY " + apiKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers); // 헤더와 본문을 포함한 요청을 생성

        ResponseEntity<Map> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, Map.class);

        System.out.println("Response: " + response.getBody());

        if (response.getBody() == null) {
            throw new IllegalStateException("API 응답이 없습니다.");
        }

        Map<String, Object> responseData = (Map<String, Object>) response.getBody().get("data");
        if (responseData == null) {
            throw new IllegalStateException("API 응답 데이터가 없습니다.");
        }

        Map<String, Object> track = (Map<String, Object>) responseData.get("track");
        if (track == null) {
            throw new IllegalStateException("track 정보가 없습니다.");
        }

        Map<String, Object> lastEvent = (Map<String, Object>) track.get("lastEvent");
        if (lastEvent == null) {
            throw new IllegalStateException("lastEvent 정보가 없습니다.");
        }

        Map<String, Object> status = (Map<String, Object>) lastEvent.get("status");
        if (status == null) {
            throw new IllegalStateException("status 정보가 없습니다.");
        }

        String statusCode = (String) status.get("code");

        if ("DELIVERED".equalsIgnoreCase(statusCode)) {
            ship.setStatus(ShippingStatus.DELIVERED);
        }

        ship.setStatusCheckDate(now);
        shipRepository.save(ship);

        return convertToDto(ship);
    }


    private ShipResponse convertToDto(Ship ship) {
        return ShipResponse.builder()
                .shipId(ship.getShipId())
                .trackingNumber(ship.getTrackingNumber())
                .carrierName(ship.getCarrier().getName())
                .carrierCode(ship.getCarrier().getCode())
                .status(ship.getStatus())
                .statusCheckDate(ship.getStatusCheckDate())
                .sellerId(ship.getSeller().getId())
                .productId(ship.getProduct().getId())
                .buyerId((ship.getBuyer().getId()))
                .build();
    }

    /*
        운송장 ID로 Ship 객체 조회
    */
    public Ship getShipById(Long shipId) {
        return shipRepository.findById(shipId)
                .orElseThrow(() -> new IllegalArgumentException("해당 운송장을 찾을 수 없습니다."));
    }


}
