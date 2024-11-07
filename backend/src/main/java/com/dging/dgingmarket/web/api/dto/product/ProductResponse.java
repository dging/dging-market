package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.enums.MainCategory;
import com.dging.dgingmarket.enums.ProductQuality;
import com.dging.dgingmarket.enums.RunningStatus;
import com.dging.dgingmarket.web.api.dto.common.ImagesResponse;
import com.dging.dgingmarket.web.api.dto.common.TagsResponse;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

/**
 * 상품 상세 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "상품 상세 조회 응답 DTO")
public class ProductResponse {

    @Schema(description = RESPONSE_ID)
    private Long id;

    @Schema(description = RESPONSE_STORE_ID)
    private Long storeId;

    @Schema(description = RESPONSE_STORE_NAME)
    private String storeName;

    @Schema(description = RESPONSE_TITLE)
    private String title;

    @Schema(description = RESPONSE_PRODUCT_CONTENT)
    private String content;

    @Schema(description = RESPONSE_FAVORITE_COUNT)
    private int favoriteCount;

    @Schema(description = RESPONSE_VIEW_COUNT)
    private int viewCount;

    @Schema(description = RESPONSE_QUALITY)
    private ProductQuality quality;

    @Schema(description = RESPONSE_QUANTITY)
    private int quantity;

    @Schema(description = RESPONSE_REGION)
    private String region;

    @Schema(description = RESPONSE_LOCATION)
    private String location;

    @Schema(description = RESPONSE_MAIN_CATEGORY)
    private MainCategory mainCategory;

    @Schema(description = RESPONSE_MIDDLE_CATEGORY)
    private String middleCategory;

    @Schema(description = RESPONSE_SUB_CATEGORY)
    private String subCategory;

    @Schema(description = RESPONSE_RUNNING_STATUS)
    private RunningStatus runningStatus;

    @Schema(description = RESPONSE_IMAGES)
    private List<ImagesResponse> images;

    @Schema(description = RESPONSE_PRICE)
    private int price;

    @Schema(description = RESPONSE_TAGS)
    private List<TagsResponse> tags;

    @Schema(description = RESPONSE_IS_TEMPORARY_SAVE, example = EXAMPLE_IS_TEMPORARY_SAVE)
    private Boolean isTemporarySave;

    @Schema(description = RESPONSE_CREATED_AT)
    private Date createdAt;

    @QueryProjection
    public ProductResponse(Long id, Long storeId, String storeName, String title, String content, int favoriteCount, int viewCount, ProductQuality quality, int quantity, String region, String location, MainCategory mainCategory, String middleCategory, String subCategory, RunningStatus runningStatus, List<ImagesResponse> images, int price, List<TagsResponse> tags, boolean isTemporarySave, Date createdAt) {
        this.id = id;
        this.storeId = storeId;
        this.storeName = storeName;
        this.title = title;
        this.content = content;
        this.favoriteCount = favoriteCount;
        this.viewCount = viewCount;
        this.quality = quality;
        this.quantity = quantity;
        this.region = region;
        this.location = location;
        this.mainCategory = mainCategory;
        this.middleCategory = middleCategory;
        this.subCategory = subCategory;
        this.runningStatus = runningStatus;
        this.images = images;
        this.price = price;
        this.tags = tags;
        this.isTemporarySave = isTemporarySave;
        this.createdAt = createdAt;
    }

    public String getMainCategory() {
        if(ObjectUtils.isEmpty(mainCategory)) {
            return null;
        } else {
            return mainCategory.getValue();
        }
    }

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
