package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.domain.common.Image;
import com.dging.dgingmarket.domain.common.Tag;
import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.store.Store;
import com.dging.dgingmarket.util.constant.ValidationMessages;
import com.dging.dgingmarket.util.enums.ProductQuality;
import com.dging.dgingmarket.util.validation.Enum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

/**
 * 상품 등록 요청 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "상품 등록 요청 DTO")
public class ProductCreateRequest {

    @Schema(description = REQUEST_IMAGE_IDS, example = "[]")
    List<Long> imageIds;

    @NotEmpty
    @Schema(description = REQUEST_TITLE, example = EXAMPLE_TITLE)
    private String title;

    @NotEmpty
    @Schema(description = REQUEST_MAIN_CATEGORY, example = EXAMPLE_MAIN_CATEGORY)
    private String mainCategory;

    @NotEmpty
    @Schema(description = REQUEST_MIDDLE_CATEGORY, example = EXAMPLE_MIDDLE_CATEGORY)
    private String middleCategory;

    @Schema(description = REQUEST_SUB_CATEGORY, example = EXAMPLE_SUB_CATEGORY)
    private String subCategory;

    @NotEmpty
    @Enum(enumClass = ProductQuality.class, ignoreCase = true, message = ValidationMessages.PRODUCT_QUALITY)
    @Schema(description = REQUEST_QUALITY, example = EXAMPLE_QUALITY)
    private String quality;

    @Schema(description = REQUEST_CONTENT, example = EXAMPLE_CONTENT)
    private String content;

    @Schema(description = REQUEST_TAGS, example = "[\"CD\", \"Rock\", \"Punk\"]")
    private List<String> tags;

    @Schema(description = REQUEST_PRICE, example = EXAMPLE_PRICE)
    private int price;

    @Schema(description = REQUEST_ALLOWS_OFFERS, example = EXAMPLE_ALLOWS_OFFERS)
    private boolean allowsOffers;

    @Schema(description = REQUEST_IS_SHIPPING_FEE_INCLUDED, example = EXAMPLE_IS_SHIPPING_FEE_INCLUDED)
    private boolean isShippingFeeIncluded;

    @Schema(description = REQUEST_IS_DIRECT_TRADE_AVAILABLE, example = EXAMPLE_IS_DIRECT_TRADE_AVAILABLE)
    private boolean isDirectTradeAvailable;

    @Schema(description = REQUEST_REGION, example = EXAMPLE_REGION)
    private String region;

    @Schema(description = REQUEST_LOCATION, example = EXAMPLE_LOCATION)
    private String location;

    @Schema(description = REQUEST_QUANTITY, example = EXAMPLE_QUANTITY)
    private int quantity;

    @Schema(description = REQUEST_IS_TEMPORARY_SAVE, example = EXAMPLE_IS_TEMPORARY_SAVE)
    private boolean isTemporarySave;

    public ProductQuality getQuality() {
        return ProductQuality.find(quality);
    }

    public Product toEntityWith(Store store, List<Image> images, List<Tag> tags) {

        if(isTemporarySave) {
            return Product.createTemporally(
                    store,
                    title,
                    content,
                    mainCategory,
                    middleCategory,
                    subCategory,
                    images,
                    tags,
                    price,
                    getQuality(),
                    quantity,
                    allowsOffers,
                    region,
                    location,
                    isDirectTradeAvailable,
                    isShippingFeeIncluded
            );
        } else {
            return Product.create(
                    store,
                    title,
                    content,
                    mainCategory,
                    middleCategory,
                    subCategory,
                    images,
                    tags,
                    price,
                    getQuality(),
                    quantity,
                    allowsOffers,
                    region,
                    location,
                    isDirectTradeAvailable,
                    isShippingFeeIncluded
            );
        }
    }
}
