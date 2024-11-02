package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.util.constant.ValidationMessages;
import com.dging.dgingmarket.util.enums.ProductQuality;
import com.dging.dgingmarket.util.validation.Enum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 상품 수정 요청 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductUpdateRequest {

    List<Long> imageIds;

    @NotNull
    private Long id;

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

}
