package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.util.enums.ProductQuality;
import com.dging.dgingmarket.util.validation.Enum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

/**
 * 상품 수정 요청 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductUpdateRequest {

    MultipartFile image;

    @NotEmpty
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String mainCategory;

    @NotEmpty
    private String middleCategory;

    private String subCategory;

    @NotEmpty
    @Enum(enumClass = ProductQuality.class, ignoreCase = true, message = "[최상, 상, 중] 중에 하나이어야 합니다.")
    private ProductQuality quality;

    private String description;

    private String tags;

    private int price;

    private boolean allowsOffers;

    private boolean isShippingFreeIncluded;

    private boolean isDirectTradeAvailable;

    private String region;

    private String location;

    private int quantity;

    private boolean isTemporarySave;

}
