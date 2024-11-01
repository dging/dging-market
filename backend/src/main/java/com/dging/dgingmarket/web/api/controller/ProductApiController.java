package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.service.ProductService;
import com.dging.dgingmarket.web.api.dto.product.ProductCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
