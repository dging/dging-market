package com.dging.dgingmarket.web.api.controller.service;

import com.dging.dgingmarket.service.ProductService;
import com.dging.dgingmarket.util.CustomPageableParameter;
import com.dging.dgingmarket.util.constant.DocumentDescriptions;
import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.product.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

@Slf4j
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "2. 상품 서비스", description = "상품 관리 API 엔드포인트")
@RestController
public class ProductApiController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "상품 등록", description = "단일 상품을 등록합니다.")
    @ApiResponse(responseCode = "201", description = "성공")
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
    @ApiResponse(responseCode = "200", description = "성공")
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
    @CustomPageableParameter
    @Operation(summary = "상품 조회", description = "여러 상품을 조회합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    ResponseEntity<Page<ProductsResponse>> fetchProducts(
            @ParameterObject Pageable pageable,
            @Valid @Schema(implementation = CommonCondition.class)
            @ParameterObject CommonCondition cond
    ) {

        Page<ProductsResponse> response = productService.products(pageable, cond);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "상품 상세 조회", description = "단일 상품 상세를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    ResponseEntity<ProductResponse> fetchProduct(
            @Parameter(description = DocumentDescriptions.REQUEST_ID)
            @PathVariable Long id
    ) {

        ProductResponse response = productService.product(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "상품 삭제", description = "단일 상품을 삭제합니다.")
    @ApiResponse(responseCode = "204", description = "성공")
    ResponseEntity<Void> delete(
            @Parameter(description = DocumentDescriptions.REQUEST_ID)
            @PathVariable Long id
    ) {

        productService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "상품 진행 상태 변경", description = "단일 상품의 진행 상태를 변경합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
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
    @ApiResponse(responseCode = "201", description = "성공")
    ResponseEntity<Void> createFavorite(
            @Parameter(description = DocumentDescriptions.REQUEST_PRODUCT_ID)
            @PathVariable Long productId
    ) {

        productService.createFavorite(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @DeleteMapping("/{productId}/favorite")
    @Operation(summary = "상품 찜 해제하기 ", description = "단일 상품을 찜 해제합니다.")
    @ApiResponse(responseCode = "204", description = "성공")
    ResponseEntity<Void> deleteFavorite(
            @Parameter(description = DocumentDescriptions.REQUEST_PRODUCT_ID)
            @PathVariable Long productId
    ) {

        productService.deleteFavorite(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/favorite")
    @CustomPageableParameter
    @Operation(summary = "찜한 상품 조회", description = "찜한 여러 상품을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    ResponseEntity<Page<FavoriteProductsResponse>> fetchFavoriteProducts(
            @ParameterObject Pageable pageable,
            @Valid @Schema(implementation = CommonCondition.class)
            @ParameterObject CommonCondition cond
    ) {

        Page<FavoriteProductsResponse> response = productService.favoriteProducts(pageable, cond);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
