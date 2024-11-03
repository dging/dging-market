package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.util.enums.RunningStatus;
import com.dging.dgingmarket.web.api.dto.common.ImageResponse;
import com.dging.dgingmarket.web.api.dto.common.TagResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

/**
 * 상점 상품 조회 응답 DTO
 * - 조회 조건에 따라 여러 API에서 공통으로 사용됨
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Schema(description = "상점 상품 조회 응답 DTO")
public class StoreProductsResponse {

    @Schema(description = RESPONSE_ID)
    private Long id;

    @Schema(description = RESPONSE_STORE_ID)
    private Long storeId;

    @Schema(description = RESPONSE_STORE_NAME)
    private String storeName;

    @Schema(description = RESPONSE_TITLE)
    private String title;

    @Schema(description = RESPONSE_FAVORITE_COUNT)
    private int favoriteCount;

    @Schema(description = RESPONSE_RUNNING_STATUS)
    private RunningStatus runningStatus;

    @Schema(description = RESPONSE_IMAGES)
    private List<ImageResponse> images;

    @Schema(description = RESPONSE_PRICE)
    private int price;

    @Schema(description = RESPONSE_TAGS)
    private List<TagResponse> tags;

    @Schema(description = RESPONSE_CREATED_AT)
    private Date createdAt;

    @Schema(description = RESPONSE_UPDATE_AT)
    private Date updatedAt;

    public String getRunningStatus() {
        if(ObjectUtils.isEmpty(runningStatus)) {
            return null;
        } else {
            return runningStatus.getValue();
        }
    }
}
