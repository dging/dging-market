package com.dging.dgingmarket.util;

import com.dging.dgingmarket.util.enums.RunningStatus;
import com.dging.dgingmarket.web.api.dto.product.ProductCreateRequest;
import com.dging.dgingmarket.web.api.dto.product.ProductRunningStatusChangeRequest;
import com.dging.dgingmarket.web.api.dto.product.ProductUpdateRequest;
import com.dging.dgingmarket.web.api.dto.store.StoreIntroductionChangeRequest;

import java.util.List;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

public class RequestFixture {

    public static final ProductCreateRequest PRODUCT_CREATE = new ProductCreateRequest(
        List.of(1L, 2L),
            EXAMPLE_PRODUCT_TITLE,
        EXAMPLE_MAIN_CATEGORY,
        EXAMPLE_MIDDLE_CATEGORY,
        EXAMPLE_SUB_CATEGORY,
        EXAMPLE_QUALITY,
            EXAMPLE_PRODUCT_CONTENT,
        List.of(EXAMPLE_TAG + 1, EXAMPLE_TAG + 2),
        Integer.parseInt(EXAMPLE_PRICE),
        Boolean.parseBoolean(EXAMPLE_ALLOWS_OFFERS),
        Boolean.parseBoolean(EXAMPLE_IS_SHIPPING_FEE_INCLUDED),
        Boolean.parseBoolean(EXAMPLE_IS_DIRECT_TRADE_AVAILABLE),
        EXAMPLE_REGION,
        EXAMPLE_LOCATION,
        Integer.parseInt(EXAMPLE_QUANTITY),
        Boolean.parseBoolean(EXAMPLE_IS_TEMPORARY_SAVE)
    );

    public static final ProductUpdateRequest PRODUCT_UPDATE = new ProductUpdateRequest(
            List.of(1L, 2L),
            "변경된 제목",
            EXAMPLE_MAIN_CATEGORY,
            EXAMPLE_MIDDLE_CATEGORY,
            EXAMPLE_SUB_CATEGORY,
            EXAMPLE_QUALITY,
            "변경된 내용",
            List.of(EXAMPLE_TAG + 1, EXAMPLE_TAG + 2),
            Integer.parseInt(EXAMPLE_PRICE),
            Boolean.parseBoolean(EXAMPLE_ALLOWS_OFFERS),
            Boolean.parseBoolean(EXAMPLE_IS_SHIPPING_FEE_INCLUDED),
            Boolean.parseBoolean(EXAMPLE_IS_DIRECT_TRADE_AVAILABLE),
            EXAMPLE_REGION,
            EXAMPLE_LOCATION,
            Integer.parseInt(EXAMPLE_PRICE),
            Boolean.parseBoolean(EXAMPLE_IS_DIRECT_TRADE_AVAILABLE)
    );

    public static final ProductRunningStatusChangeRequest PRODUCT_RUNNING_STATUS_CHANGE = new ProductRunningStatusChangeRequest(
            RunningStatus.AVAILABLE.getValue()
    );

    public static final StoreIntroductionChangeRequest STORE_INTRODUCTION_CHANGE = new StoreIntroductionChangeRequest(EXAMPLE_STORE_INTRODUCTION);
}
