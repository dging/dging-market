package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.service.ProductService;
import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.product.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;

    @PostMapping
    ResponseEntity<Void> create(@Valid ProductCreateRequest request) {

        productService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping
    ResponseEntity<Void> update(@Valid ProductUpdateRequest request) {

        productService.update(request);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping
    ResponseEntity<Page<ProductsResponse>> fetchProducts(Pageable pageable, @Valid CommonCondition cond) {

        Page<ProductsResponse> response = productService.products(pageable, cond);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductResponse> fetchProduct(@PathVariable Long id) {

        ProductResponse response = productService.product(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {

        productService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<Void> changeRunningStatus(@PathVariable Long id, @Valid @RequestBody ProductRunningStatusChangeRequest request) {

        productService.changeRunningStatus(id, request.getRunningStatus());

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/{productId}/favorite")
    ResponseEntity<Void> createFavorite(@PathVariable Long productId) {

        productService.createFavorite(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @DeleteMapping("/{productId}/favorite")
    ResponseEntity<Void> deleteFavorite(@PathVariable Long productId) {

        productService.deleteFavorite(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/favorite")
    ResponseEntity<Page<FavoriteProductsResponse>> fetchFavoriteProducts(Pageable pageable, @Valid CommonCondition cond) {

        Page<FavoriteProductsResponse> response = productService.favoriteProducts(pageable, cond);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
