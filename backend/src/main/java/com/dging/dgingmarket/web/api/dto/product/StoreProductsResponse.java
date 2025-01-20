package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.domain.type.RunningStatus;
import com.dging.dgingmarket.web.api.dto.ImagesResponse;
import com.dging.dgingmarket.web.api.dto.TagsResponse;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
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
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "상점 상품 조회 응답 DTO")
public class StoreProductsResponse {

    @Schema(description = RESPONSE_ID, example = EXAMPLE_ID)
    private Long id;

    @Schema(description = RESPONSE_STORE_ID, example = EXAMPLE_ID)
    private Long storeId;

    @Schema(description = RESPONSE_STORE_NAME, example = EXAMPLE_STORE_NAME)
    private String storeName;

    @Schema(description = RESPONSE_TITLE, example = EXAMPLE_PRODUCT_TITLE)
    private String title;

    @Schema(description = RESPONSE_FAVORITE_COUNT, example = EXAMPLE_FAVORITE_COUNT)
    private int favoriteCount;

    @Schema(description = RESPONSE_RUNNING_STATUS, example = EXAMPLE_RUNNING_STATUS)
    private RunningStatus runningStatus;

    @Schema(description = RESPONSE_IMAGES, example = "[]")
    private List<ImagesResponse> images;

    @Schema(description = RESPONSE_PRICE, example = EXAMPLE_PRICE)
    private int price;

    @Schema(description = RESPONSE_TAGS, example = "[]")
    private List<TagsResponse> tags;

    @Schema(description = RESPONSE_IS_TEMPORARY_SAVE, example = EXAMPLE_IS_TEMPORARY_SAVE)
    private Boolean isTemporarySave;

    @Schema(description = RESPONSE_CREATED_AT, example = EXAMPLE_CREATED_AT)
    private Date createdAt;

    @Schema(description = RESPONSE_UPDATE_AT, example = EXAMPLE_UPDATED_AT)
    private Date updatedAt;

    @QueryProjection
    public StoreProductsResponse(Long id, Long storeId, String storeName, String title, int favoriteCount, RunningStatus runningStatus, List<ImagesResponse> images, int price, List<TagsResponse> tags, Boolean isTemporarySave, Date createdAt, Date updatedAt) {
        this.id = id;
        this.storeId = storeId;
        this.storeName = storeName;
        this.title = title;
        this.favoriteCount = favoriteCount;
        this.runningStatus = runningStatus;
        this.images = images;
        this.price = price;
        this.tags = tags;
        this.isTemporarySave = isTemporarySave;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getRunningStatus() {
        if(ObjectUtils.isEmpty(runningStatus)) {
            return null;
        } else {
            return runningStatus.getValue();
        }
    }
}
