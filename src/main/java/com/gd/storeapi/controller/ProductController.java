package com.gd.storeapi.controller;

import com.gd.storeapi.dto.ProductDto;
import com.gd.storeapi.service.ProductsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductsService productsService;

    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping()
    public ResponseEntity<String> getAll() {
        List<ProductDto> products = productsService.getAll();
        return ResponseEntity.ok("List of products");
    }
}
