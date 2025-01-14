package com.dging.dgingmarket.pay.repository;

import com.dging.dgingmarket.pay.entity.TossPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TossPaymentRepository extends JpaRepository<TossPayment, Long> {
    // 이미 결제된 상품이 있는지 확인
    boolean existsByProductIdAndTossPaymentStatus(Long productId, String tossPaymentStatus);
}

