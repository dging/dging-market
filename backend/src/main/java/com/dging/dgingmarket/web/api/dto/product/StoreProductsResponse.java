package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.util.enums.RunningStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreProductsResponse {

    private String seq;
    private String title;
    private int favoriteCount;
    private RunningStatus runningStatus;
    private String imageUrl;
    private int price;
    private String tags;
    private Date createdAt;
    private Date updatedAt;

    public String getRunningStatus() {
        if(ObjectUtils.isEmpty(runningStatus)) {
            return null;
        } else {
            return runningStatus.getValue();
        }
    }
}
