package com.dging.dgingmarket.web.api.controller.service;

import com.dging.dgingmarket.docs.CustomDescriptionOverride;
import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.exception.ApiErrorCodeExample;
import com.dging.dgingmarket.exception.StoreErrorCode;
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
import static com.dging.dgingmarket.exception.StoreErrorCode._REVIEW_MYSELF_ERROR;
import static com.dging.dgingmarket.exception.UserErrorCode._USER_NOT_FOUND;

@Slf4j
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Tag(name = "4. 후기", description = "후기 API 엔드포인트")
@RestController
public class ReviewApiController {

    private final ProductService productService;
    private final StoreService storeService;

    /**
     * API for test
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "상품 후기 삭제", description = "단일 상품 후기를 삭제합니다.")
    @ApiResponses(@ApiResponse(responseCode = "204", description = "성공"))
    @ApiErrorCodeExample({StoreErrorCode._REVIEW_NOT_FOUND, StoreErrorCode._USER_OWN_REVIEW_ERROR})
    ResponseEntity<Void> deleteReview(
            @Parameter(description = DocumentDescriptions.REQUEST_PRODUCT_ID)
            @PathVariable Long id
    ) {

        storeService.deleteProductReview(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
