package com.gd.storeapi.controller;

import com.gd.storeapi.dto.ProductDto;
import com.gd.storeapi.dto.ProductsResponse;
import com.gd.storeapi.service.ProductsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductsResponse> getAll() {
        ProductsResponse response = new ProductsResponse();
        response.setProducts(productsService.getAll());
        return ResponseEntity.ok(response);
    }

/*    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getAll() {
        return productsService.getAll();
    }*/
}