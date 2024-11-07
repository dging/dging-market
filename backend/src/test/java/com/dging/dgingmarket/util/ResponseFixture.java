package com.dging.dgingmarket.util;

import com.dging.dgingmarket.util.constant.BasePaths;
import com.dging.dgingmarket.util.enums.ImageType;
import com.dging.dgingmarket.util.enums.ProductQuality;
import com.dging.dgingmarket.util.enums.RunningStatus;
import com.dging.dgingmarket.web.api.dto.common.ImageResponse;
import com.dging.dgingmarket.web.api.dto.common.ImagesResponse;
import com.dging.dgingmarket.web.api.dto.common.TagsResponse;
import com.dging.dgingmarket.web.api.dto.product.FavoriteProductsResponse;
import com.dging.dgingmarket.web.api.dto.product.ProductResponse;
import com.dging.dgingmarket.web.api.dto.product.ProductsResponse;
import com.dging.dgingmarket.web.api.dto.product.StoreProductsResponse;
import com.dging.dgingmarket.web.api.dto.store.FollowersResponse;
import com.dging.dgingmarket.web.api.dto.store.FollowingsResponse;

import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

public class ResponseFixture {

    public static final ProductResponse PRODUCT = new ProductResponse(
            Long.parseLong(EXAMPLE_ID),
            Long.parseLong(EXAMPLE_ID),
            EXAMPLE_STORE_NAME,
            EXAMPLE_TITLE,
            EXAMPLE_CONTENT,
            Integer.parseInt(EXAMPLE_FAVORITE_COUNT),
            Integer.parseInt(EXAMPLE_VIEW_COUNT),
            ProductQuality.find(EXAMPLE_QUALITY),
            Integer.parseInt(EXAMPLE_QUANTITY),
            EXAMPLE_REGION,
            EXAMPLE_LOCATION,
            EXAMPLE_MAIN_CATEGORY,
            EXAMPLE_MIDDLE_CATEGORY,
            EXAMPLE_SUB_CATEGORY,
            RunningStatus.AVAILABLE,
            List.of(new ImagesResponse(1L, EXAMPLE_IMAGE_URL), new ImagesResponse(2L, EXAMPLE_IMAGE_URL)),
            Integer.parseInt(EXAMPLE_PRICE),
            List.of(new TagsResponse(1L, EXAMPLE_TAG + 1), new TagsResponse(2L, EXAMPLE_TAG + 2)),
            new Date()
    );

    public static final List<StoreProductsResponse> STORE_PRODUCTS = LongStream.range(1L, 20L).mapToObj(i ->
            StoreProductsResponse.builder()
                    .id(i)
                    .storeId(1L)
                    .storeName(EXAMPLE_STORE_NAME)
                    .price(Integer.parseInt(EXAMPLE_PRICE))
                    .title(EXAMPLE_TITLE)
                    .favoriteCount(Integer.parseInt(EXAMPLE_FAVORITE_COUNT))
                    .runningStatus(RunningStatus.AVAILABLE)
                    .tags(List.of(new TagsResponse(2 * i - 1, EXAMPLE_TAG + (2 * i - 1)), new TagsResponse(2 * i, EXAMPLE_TAG + 2 * i)))
                    .images(List.of(new ImagesResponse(2 * i - 1, EXAMPLE_IMAGE_URL), new ImagesResponse(2 * i, EXAMPLE_IMAGE_URL)))
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build()
    ).toList();

    public static final List<FavoriteProductsResponse> FAVORITE_PRODUCTS = LongStream.range(1L, 20L).mapToObj(i ->
            FavoriteProductsResponse.builder()
                    .id(i)
                    .storeId(i)
                    .storeName(EXAMPLE_STORE_NAME)
                    .price(Integer.parseInt(EXAMPLE_PRICE))
                    .title(EXAMPLE_TITLE)
                    .runningStatus(RunningStatus.AVAILABLE)
                    .tags(List.of(new TagsResponse(2 * i - 1, EXAMPLE_TAG + (2 * i - 1)), new TagsResponse(2 * i, EXAMPLE_TAG + 2 * i)))
                    .images(List.of(new ImagesResponse(2 * i - 1, EXAMPLE_IMAGE_URL), new ImagesResponse(2 * i, EXAMPLE_IMAGE_URL)))
                    .createdAt(new Date())
                    .build()
    ).toList();

    public static final List<ProductsResponse> PRODUCTS = LongStream.range(1L, 20L).mapToObj(i ->
            ProductsResponse.builder()
                    .id(i)
                    .storeId(i)
                    .storeName(EXAMPLE_STORE_NAME)
                    .price(Integer.parseInt(EXAMPLE_PRICE))
                    .title(EXAMPLE_TITLE)
                    .runningStatus(RunningStatus.AVAILABLE)
                    .tags(List.of(new TagsResponse(2 * i - 1, EXAMPLE_TAG + (2 * i - 1)), new TagsResponse(2 * i, EXAMPLE_TAG + 2 * i)))
                    .images(List.of(new ImagesResponse(2 * i - 1, EXAMPLE_IMAGE_URL), new ImagesResponse(2 * i, EXAMPLE_IMAGE_URL)))
                    .createdAt(new Date())
                    .build()
    ).toList();

    public static final ImageResponse IMAGE = ImageResponse.builder()
            .id(Long.parseLong(EXAMPLE_ID))
            .type(ImageType.PRODUCT)
            .fileName("fileName" + 1)
            .path(EXAMPLE_ID + "/" + BasePaths.BASE_PATH_PRODUCT + "/" + "fileName" + 1)
            .url("http://www.example.com/" + EXAMPLE_ID + "/" + BasePaths.BASE_PATH_PRODUCT + "/" + "fileName" + 1)
            .size(1024)
            .createdAt(new Date())
            .build();

    public static final ImagesResponse IMAGES = ImagesResponse.builder()
            .id(Long.parseLong(EXAMPLE_ID))
            .url("http://www.example.com/" + EXAMPLE_ID + "/" + BasePaths.BASE_PATH_PRODUCT + "/" + "fileName" + 1)
            .build();

    public static final List<FollowersResponse> FOLLOWERS = IntStream.rangeClosed(1, 20).mapToObj(i ->
            FollowersResponse.builder()
                    .storeId(1L)
                    .storeName(EXAMPLE_STORE_NAME)
                    .rating(Float.parseFloat(EXAMPLE_RATING_AVERAGE))
                    .salesCount(Integer.parseInt(EXAMPLE_SALES_COUNT))
                    .followersCount(Integer.parseInt(EXAMPLE_FOLLOWERS_COUNT))
                    .build()
    ).toList();

    public static final List<FollowingsResponse> FOLLOWINGS = IntStream.rangeClosed(1, 20).mapToObj(i ->
            FollowingsResponse.builder()
                    .storeId(1L)
                    .storeName(EXAMPLE_STORE_NAME)
                    .salesCount(Integer.parseInt(EXAMPLE_SALES_COUNT))
                    .createdAt(new Date())
                    .build()
    ).toList();


}
