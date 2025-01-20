package com.dging.dgingmarket.web.api.controller.service;

import com.dging.dgingmarket.documentation.ApiErrorCodeExample;
import com.dging.dgingmarket.exception.StoreErrorCode;
import com.dging.dgingmarket.service.ProductService;
import com.dging.dgingmarket.service.StoreService;
import com.dging.dgingmarket.util.constant.DocumentDescriptions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
