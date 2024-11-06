package com.dging.dgingmarket.web.api.controller.service;

import com.dging.dgingmarket.exception.ApiErrorCodeExample;
import com.dging.dgingmarket.exception.StoreErrorCode;
import com.dging.dgingmarket.exception.UserErrorCode;
import com.dging.dgingmarket.service.ProductService;
import com.dging.dgingmarket.service.StoreService;
import com.dging.dgingmarket.util.CustomPageableParameter;
import com.dging.dgingmarket.util.constant.DocumentDescriptions;
import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.product.StoreProductsResponse;
import com.dging.dgingmarket.web.api.dto.store.FollowersResponse;
import com.dging.dgingmarket.web.api.dto.store.FollowingsResponse;
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

@Slf4j
@RestController
@RequestMapping("/stores")
@Tag(name = "3. 상점 서비스", description = "상점 관리 API 엔드포인트")
@RequiredArgsConstructor
public class StoreApiController {

    private final ProductService productService;
    private final StoreService storeService;

    @GetMapping("/{id}/products")
    @CustomPageableParameter
    @Operation(summary = "상점 상품 조회", description = "여러 상점 상품을 조회합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "성공"))
    ResponseEntity<Page<StoreProductsResponse>> fetchStoreProducts(
            @Parameter(description = DocumentDescriptions.REQUEST_ID)
            @PathVariable Long id,
            @ParameterObject Pageable pageable,
            @Valid @Schema(implementation = CommonCondition.class)
            @ParameterObject CommonCondition cond
    ) {

        Page<StoreProductsResponse> response = productService.storeProducts(pageable, id, cond);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{id}/followers")
    @Operation(summary = "상점 팔로우", description = "상점을 팔로우합니다.")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "성공"))
    @ApiErrorCodeExample({
            StoreErrorCode._FOLLOW_MYSELF_ERROR, 
            UserErrorCode._USER_NOT_FOUND,
            StoreErrorCode._ALREADY_FOLLOWED,
            StoreErrorCode._STORE_NOT_FOUND
    })
    ResponseEntity<Void> follow(
            @Parameter(description = DocumentDescriptions.REQUEST_ID)
            @PathVariable Long id
    ) {

        storeService.follow(id);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/followers")
    @Operation(summary = "상점 언팔로우", description = "상점을 언팔로우합니다.")
    @ApiResponses(@ApiResponse(responseCode = "204", description = "성공"))
    @ApiErrorCodeExample({StoreErrorCode._FOLLOWER_NOT_FOUND, UserErrorCode._USER_NOT_FOUND, StoreErrorCode._STORE_NOT_FOUND})
    ResponseEntity<Void> unfollow(
            @Parameter(description = DocumentDescriptions.REQUEST_ID)
            @PathVariable Long id
    ) {

        storeService.unfollow(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/followers")
    @CustomPageableParameter
    @Operation(summary = "팔로워 조회", description = "상점을 팔로우한 여러 사용자를 조회합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "성공"))
    ResponseEntity<Page<FollowersResponse>> fetchFollowers(
            @Parameter(description = DocumentDescriptions.REQUEST_ID)
            @PathVariable Long id,
            @ParameterObject Pageable pageable,
            @Valid @Schema(implementation = CommonCondition.class)
            @ParameterObject CommonCondition cond
    ) {

        Page<FollowersResponse> response = storeService.followers(pageable, id, cond);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}/followings")
    @CustomPageableParameter
    @Operation(summary = "팔로잉 조회", description = "상점이 팔로우한 여러 사용자를 조회합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "성공"))
    ResponseEntity<Page<FollowingsResponse>> fetchFollowings(
            @Parameter(description = DocumentDescriptions.REQUEST_ID)
            @PathVariable Long id,
            @ParameterObject Pageable pageable,
            @Valid @Schema(implementation = CommonCondition.class)
            @ParameterObject CommonCondition cond
    ) {

        Page<FollowingsResponse> response = storeService.followings(pageable, id, cond);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
