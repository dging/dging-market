package com.dging.dgingmarket.web.api.dto.store;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 상점 후기 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreReviewsResponse {

    private Long id;

    private Long storeId;

    private String storeName;

    private String profileImageUrl;

    private int rating;

    private Long productId;

    private String productName;

    private String content;

    private Date createdAt;

}
