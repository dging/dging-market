package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.util.enums.ProductQuality;
import com.dging.dgingmarket.util.enums.RunningStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * 상품 상세 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String title;
    private String content;
    private int favoriteCount;
    private String viewCount;
    private ProductQuality quality;
    private int quantity;
    private String region;
    private String location;
    private String mainCategory;
    private String middleCategory;
    private String subCategory;
    private RunningStatus runningStatus;
    private String imageUrl;
    private int price;
    private String tags;
//    private UserResponse author;
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
