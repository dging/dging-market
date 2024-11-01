package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.domain.common.Image;
import com.dging.dgingmarket.domain.common.Tag;
import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.store.Store;
import com.dging.dgingmarket.util.constant.ValidationMessages;
import com.dging.dgingmarket.util.enums.ProductQuality;
import com.dging.dgingmarket.util.validation.Enum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 상품 등록 요청 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCreateRequest {

    List<Long> imageIds;

    @NotEmpty
    private String title;

    @NotEmpty
    private String mainCategory;

    @NotEmpty
    private String middleCategory;

    private String subCategory;

    @NotEmpty
    @Enum(enumClass = ProductQuality.class, ignoreCase = true, message = ValidationMessages.PRODUCT_QUALITY)
    private String quality;

    private String content;

    private List<String> tags;

    private int price;

    private boolean allowsOffers;

    private boolean isShippingFreeIncluded;

    private boolean isDirectTradeAvailable;

    private String region;

    private String location;

    private int quantity;

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
                    isShippingFreeIncluded
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
                    isShippingFreeIncluded
            );
        }
    }
}
