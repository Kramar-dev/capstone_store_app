package com.gd.storeapi.controller;

import com.gd.storeapi.dto.ProductDto;
import com.gd.storeapi.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/cart/items")
public class CartController {

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    private final CartService cartService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll() {
        List<ProductDto> products = cartService.getAll();
        return ResponseEntity.ok("List of items in the cart");
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add() {
        cartService.add(new ProductDto());
        return ResponseEntity.ok("Item has been added to the cart");
    }

    @PutMapping(path = "/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@PathVariable("itemId") String itemId) {
        cartService.update();
        return ResponseEntity.ok("Cart has been modified");
    }

    @DeleteMapping(path = "/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> remove(@PathVariable("itemId") String itemId) {
        cartService.remove(new ProductDto());
        return ResponseEntity.ok("Item has been removed from the cart");
    }
}