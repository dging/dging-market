package com.dging.dgingmarket.web.api.dto.store;

import com.dging.dgingmarket.domain.common.enums.RunningStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * 판매 내역 상세 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SalesRecordResponse {

    private Product product;
    private Payment payment;
    private Order order;

    /**
     * 상품 정보
     */
    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Product {
        private Long id;
        private Long storeId;
        private String storeName;
        private String title;
        private RunningStatus runningStatus;
        private String imageUrl;
        private int price;
        private Date createdAt;

        public String getRunningStatus() {
            if(ObjectUtils.isEmpty(runningStatus)) {
                return null;
            } else {
                return runningStatus.getValue();
            }
        }
    }

    /**
     * 판매 정보
     */
    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Payment {
        private Long id;
        private int pendingAmount;
        private int paidAmount;
        private Date paidAt;
        private String accountNumber;
        private String bankName;
        private String accountHolderName; //예금주명
    }

    /**
     * 거래 정보
     */
    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Order {
        private Long id;
        private String number; //주문번호
        private Date orderedAt;
        private Long buyerId;
        private String buyerName;
        private String transactionMethod; //거래방법
        private String courierName; //택배사
        private String trackingNumber; //운송장번호
    }

}
