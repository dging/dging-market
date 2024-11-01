package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.service.ProductService;
import com.dging.dgingmarket.service.cloud.FileUploadService;
import com.dging.dgingmarket.web.api.dto.product.ProductCreateRequest;
import com.dging.dgingmarket.web.api.dto.product.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;
    private final FileUploadService fileUploadService;

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
}
