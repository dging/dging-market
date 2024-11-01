package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.util.enums.RunningStatus;
import com.dging.dgingmarket.web.api.dto.common.ImageResponse;
import com.dging.dgingmarket.web.api.dto.common.TagResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

/**
 * 상품 조회 응답 DTO
 * - 조회 조건에 따라 여러 API에서 공통으로 사용됨
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductsResponse {

    private Long id;
    private Long storeId;
    private String storeName;
    private String title;
    private RunningStatus runningStatus;
    private int price;
    private List<ImageResponse> imageUrls;
    private List<TagResponse> tags;
    private Date createdAt;

    public String getRunningStatus() {
        if(ObjectUtils.isEmpty(runningStatus)) {
            return null;
        } else {
            return runningStatus.getValue();
        }
    }
}
