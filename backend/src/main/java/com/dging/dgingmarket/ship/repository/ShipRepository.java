package com.dging.dgingmarket.ship.repository;

import com.dging.dgingmarket.ship.entity.Ship;
import com.dging.dgingmarket.ship.enums.CarrierType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShipRepository extends JpaRepository<Ship, Long> {

    // 구매자 ID로 운송장 조회
    List<Ship> findByBuyer_Id(Long buyerId);

    // 판매자 ID로 운송장 조회
    List<Ship> findBySeller_Id(Long storeId);

    // 상품 ID로 조회(중복방지)
    boolean existsByProduct_Id(Long productId);

    // 특정 택배사 코드와 운송장 번호로 운송 정보 조회
    Optional<Ship> findByCarrierAndTrackingNumber(CarrierType carrier, String trackingNumber);

}
