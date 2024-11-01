package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.service.ProductService;
import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.product.StoreProductsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreApiController {

    private final ProductService productService;

    @GetMapping("/{id}/products")
    ResponseEntity<Page<StoreProductsResponse>> fetchStoreProducts(Pageable pageable, @PathVariable Long id, @Valid CommonCondition cond) {

        Page<StoreProductsResponse> response = productService.storeProducts(pageable, id, cond);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
