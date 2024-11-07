package com.dging.dgingmarket.web.api.dto.store;

import com.dging.dgingmarket.domain.common.enums.PaymentType;
import com.dging.dgingmarket.domain.common.enums.RunningStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * 정산 내역 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SettlementRecordsResponse {

    private Long id;
    private Long sellerId;
    private String sellerName;
    private Long buyerId;
    private String buyerName;
    private String title;
    private RunningStatus runningStatus;
    private String productImageUrl;
    private int price;
    private PaymentType paymentType;
    private String accountNumber;
    private String bankName;
    private Date createdAt;
    private Date paidAt;

    public String getRunningStatus() {
        if(ObjectUtils.isEmpty(runningStatus)) {
            return null;
        } else {
            return runningStatus.getValue();
        }
    }

    public String getPaymentType() {
        if(ObjectUtils.isEmpty(paymentType)) {
            return null;
        } else {
            return paymentType.getValue();
        }
    }
}
