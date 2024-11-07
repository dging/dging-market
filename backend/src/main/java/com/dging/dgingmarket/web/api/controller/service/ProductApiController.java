package com.dging.dgingmarket.web.api.controller.service;

import com.dging.dgingmarket.docs.CustomDescriptionOverride;
import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.store.Follower;
import com.dging.dgingmarket.exception.ApiErrorCodeExample;
import com.dging.dgingmarket.service.ProductService;
import com.dging.dgingmarket.service.StoreService;
import com.dging.dgingmarket.util.annotation.CustomPageableParameter;
import com.dging.dgingmarket.util.constant.DocumentDescriptions;
import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.product.*;
import com.dging.dgingmarket.web.api.dto.store.StoreReviewCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dging.dgingmarket.exception.CommonErrorCode._SOME_FILE_NOT_UPLOADED;
import static com.dging.dgingmarket.exception.CommonErrorCode._SOME_FILE_NOT_YOURS;
import static com.dging.dgingmarket.exception.ProductErrorCode._PRODUCT_NOT_FOUND;
import static com.dging.dgingmarket.exception.ProductErrorCode._USER_OWN_PRODUCT;
import static com.dging.dgingmarket.exception.StoreErrorCode.*;
import static com.dging.dgingmarket.exception.UserErrorCode._USER_NOT_FOUND;

@Slf4j
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "2. 상품 서비스", description = "상품 관리 API 엔드포인트")
@RestController
public class ProductApiController {

    private final ProductService productService;
    private final StoreService storeService;

    @PostMapping
    @Operation(summary = "상품 등록", description = "단일 상품을 등록합니다.")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "성공"))
    @ApiErrorCodeExample({
            _USER_NOT_FOUND,
            _SOME_FILE_NOT_UPLOADED,
            _SOME_FILE_NOT_YOURS,
    })
    ResponseEntity<Void> create(
            @Valid
            @RequestBody @Schema(implementation = ProductCreateRequest.class)
            ProductCreateRequest request
    ) {

        productService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "상품 수정", description = "단일 상품을 수정합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "성공"))
    @ApiErrorCodeExample({
            _USER_NOT_FOUND,
            _SOME_FILE_NOT_UPLOADED,
            _SOME_FILE_NOT_YOURS,
            _PRODUCT_NOT_FOUND,
            _USER_OWN_PRODUCT,
    })
    ResponseEntity<Void> update(
            @Parameter(description = DocumentDescriptions.REQUEST_ID)
            @PathVariable Long id,
            @Valid @RequestBody
            @Schema(implementation = ProductUpdateRequest.class)
            ProductUpdateRequest request
    ) {

        productService.update(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping
    @CustomPageableParameter(sortCriteria = Product.class)
    @Operation(summary = "상품 조회", description = "여러 상품을 조회합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "성공"))
    @ApiErrorCodeExample({
            _USER_NOT_FOUND,
            _SOME_FILE_NOT_UPLOADED,
            _SOME_FILE_NOT_YOURS,
            _PRODUCT_NOT_FOUND,
            _USER_OWN_PRODUCT,
    })
    ResponseEntity<Page<ProductsResponse>> fetchProducts(
            @ParameterObject Pageable pageable,
            @Valid @Schema(implementation = CommonCondition.class)
            @CustomDescriptionOverride(fieldName = "query", description = DocumentDescriptions.CONDITION_PRODUCTS_QUERY)
            @ParameterObject CommonCondition cond
    ) {

        Page<ProductsResponse> response = productService.products(pageable, cond);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "상품 상세 조회", description = "단일 상품 상세를 조회합니다. 조회수가 1 증가합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "성공"))
    @ApiErrorCodeExample({
            _PRODUCT_NOT_FOUND,
    })
    ResponseEntity<ProductResponse> fetchProduct(
            @Parameter(description = DocumentDescriptions.REQUEST_ID)
            @PathVariable Long id
    ) {

        ProductResponse response = productService.product(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "상품 삭제", description = "단일 상품을 삭제합니다.")
    @ApiResponses(@ApiResponse(responseCode = "204", description = "성공"))
    @ApiErrorCodeExample({
            _PRODUCT_NOT_FOUND,
            _USER_OWN_PRODUCT,
    })
    ResponseEntity<Void> delete(
            @Parameter(description = DocumentDescriptions.REQUEST_ID)
            @PathVariable Long id
    ) {

        productService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "상품 진행 상태 변경", description = "단일 상품의 진행 상태를 변경합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "성공"))
    @ApiErrorCodeExample({
            _USER_NOT_FOUND,
            _PRODUCT_NOT_FOUND,
            _USER_OWN_PRODUCT,
    })
    ResponseEntity<Void> changeRunningStatus(

            @Parameter(description = DocumentDescriptions.REQUEST_ID)
            @PathVariable Long id,

            @Valid @RequestBody
            @Schema(implementation = ProductRunningStatusChangeRequest.class)
            ProductRunningStatusChangeRequest request
    ) {

        productService.changeRunningStatus(id, request.getRunningStatus());

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/{productId}/favorite")
    @Operation(summary = "상품 찜하기", description = "단일 상품을 찜합니다.")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "성공"))
    @ApiErrorCodeExample({
            _USER_NOT_FOUND,
            _PRODUCT_NOT_FOUND,
    })
    ResponseEntity<Void> createFavorite(
            @Parameter(description = DocumentDescriptions.REQUEST_PRODUCT_ID)
            @PathVariable Long productId
    ) {

        productService.createFavorite(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @DeleteMapping("/{id}/favorite")
    @Operation(summary = "상품 찜 해제하기 ", description = "단일 상품을 찜 해제합니다.")
    @ApiResponses(@ApiResponse(responseCode = "204", description = "성공"))
    @ApiErrorCodeExample({
            _USER_NOT_FOUND,
            _PRODUCT_NOT_FOUND,
    })
    ResponseEntity<Void> deleteFavorite(
            @Parameter(description = DocumentDescriptions.REQUEST_PRODUCT_ID)
            @PathVariable Long id
    ) {

        productService.deleteFavorite(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/favorite")
    @CustomPageableParameter(sortCriteria = Product.class)
    @Operation(summary = "찜한 상품 조회", description = "찜한 여러 상품을 조회합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "성공"))
    @ApiErrorCodeExample({
            _USER_NOT_FOUND,
    })
    ResponseEntity<Page<FavoriteProductsResponse>> fetchFavoriteProducts(
            @ParameterObject Pageable pageable,
            @Valid @Schema(implementation = CommonCondition.class)
            @CustomDescriptionOverride(fieldName = "query", description = DocumentDescriptions.CONDITION_FAVORITE_PRODUCTS_QUERY)
            @ParameterObject CommonCondition cond
    ) {

        Page<FavoriteProductsResponse> response = productService.favoriteProducts(pageable, cond);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{id}/reviews")
    @Operation(summary = "상품 후기 작성", description = "거래가 완료된 상품에 대해 후기를 작성합니다.")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "성공"))
    @ApiErrorCodeExample({
            _USER_NOT_FOUND,
            _PRODUCT_NOT_FOUND,
            _REVIEW_MYSELF_ERROR,
    })
    ResponseEntity<Void> createReview(
            @Parameter(description = DocumentDescriptions.REQUEST_PRODUCT_ID)
            @PathVariable Long id,
            @Valid @RequestBody
            @Schema(implementation = StoreReviewCreateRequest.class)
            StoreReviewCreateRequest request
    ) {

        storeService.createProductReview(id, request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
