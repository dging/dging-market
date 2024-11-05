package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.domain.common.Image;
import com.dging.dgingmarket.domain.common.Tag;
import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.util.EntityUtils;
import com.dging.dgingmarket.util.enums.ImageType;
import com.dging.dgingmarket.util.enums.ProductQuality;
import com.dging.dgingmarket.util.enums.RunningStatus;
import com.dging.dgingmarket.web.api.dto.common.ImagesResponse;
import com.dging.dgingmarket.web.api.dto.common.TagsResponse;
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
 * 상품 상세 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    @Schema(description = RESPONSE_CONTENT)
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
    private String mainCategory;

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

    @Schema(description = RESPONSE_CREATED_AT)
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

    public Product toProductWith() {

        User user = EntityUtils.userThrowable();
        return Product.create(
                user.getStore(),
                title,
                content,
                mainCategory,
                middleCategory,
                subCategory,
                images.stream().map(v -> Image.create(user, ImageType.PRODUCT, "fileName", "path", v.getUrl(), 0)).toList(),
                tags.stream().map(v -> Tag.create(v.getName())).toList(),
                price,
                quality,
                quantity,
                Boolean.parseBoolean(EXAMPLE_ALLOWS_OFFERS),
                region,
                location,
                Boolean.parseBoolean(EXAMPLE_IS_DIRECT_TRADE_AVAILABLE),
                Boolean.parseBoolean(EXAMPLE_IS_SHIPPING_FEE_INCLUDED));
    }
}
