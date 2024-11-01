package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.service.ProductService;
import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.product.ProductCreateRequest;
import com.dging.dgingmarket.web.api.dto.product.ProductResponse;
import com.dging.dgingmarket.web.api.dto.product.ProductUpdateRequest;
import com.dging.dgingmarket.web.api.dto.product.ProductsResponse;
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
}
