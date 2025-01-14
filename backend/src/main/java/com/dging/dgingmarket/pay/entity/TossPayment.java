package com.dging.dgingmarket.pay.entity;

import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.store.Store;
import com.dging.dgingmarket.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "TBL_TossPayment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TossPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(nullable = false)
    private String orderName; // 주문 상품명

    @Column(nullable = false, length = 255)
    private String tossOrderId; // 토스 주문 ID

    @Column(nullable = false, length = 255)
    private String tossPaymentKey; // 토스 결제 키

    @Column(nullable = false, length = 50)
    private String tossPaymentStatus; // 결제 상태

    @Column(nullable = false)
    private Long totalAmount; // 결제 금액

    @Column(nullable = true) // 승인 시간은 nullable로 설정
    private String approvedAt; // 승인 시간

    @Column(nullable = false)
    private String requestedAt; // 요청 시간

    // 구매자 참조
    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false) // User 테이블의 id 참조
    private User buyer;

    // 판매자 참조
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false) // store 테이블의 id 참조
    private Store seller;

    // 상품 참조
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false) // Product 테이블의 id 참조
    private Product product;


    public static TossPayment from(Long buyerId, Long storeId, Long productId, String tossOrderId, String orderName,
                                   String tossPaymentKey, String tossPaymentStatus, Long totalAmount,
                                   String approvedAt, String requestedAt, User buyer, Store seller, Product product) {
        return TossPayment.builder()
                .tossOrderId(tossOrderId)
                .orderName(orderName)
                .tossPaymentKey(tossPaymentKey)
                .tossPaymentStatus(tossPaymentStatus)
                .totalAmount(totalAmount)
                .approvedAt(approvedAt)
                .requestedAt(requestedAt)
                .buyer(buyer)
                .seller(seller)
                .product(product)
                .build();
    }
}