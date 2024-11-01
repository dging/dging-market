package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.util.enums.ProductQuality;
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
 * 상품 상세 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private Long storeId;
    private String storeName;
    private String title;
    private String content;
    private int favoriteCount;
    private int viewCount;
    private ProductQuality quality;
    private int quantity;
    private String region;
    private String location;
    private String mainCategory;
    private String middleCategory;
    private String subCategory;
    private RunningStatus runningStatus;
    private List<ImageResponse> imageUrls;
    private int price;
    private List<TagResponse> tags;
    private Date createdAt;

    public String getQuality() {
        if(ObjectUtils.isEmpty(quality)) {
            return null;
        } else {
            return quality.getValue();
        }
    }

    public String getRunningStatus() {
        if(ObjectUtils.isEmpty(runningStatus)) {
            return null;
        } else {
            return runningStatus.getValue();
        }
    }
}
