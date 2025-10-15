package com.gd.storeapi.controller;

import com.gd.storeapi.dto.ProductDto;
import com.gd.storeapi.service.CartService;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping()
    public ResponseEntity<String> getAll() {
        List<ProductDto> products = cartService.getAll();
        return ResponseEntity.ok("List of items in the cart");
    }

    @PostMapping()
    public ResponseEntity<String> add() {
        cartService.add(new ProductDto());
        return ResponseEntity.ok("Item has been added to the cart");
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<String> update(@PathVariable("itemId") String itemId) {
        cartService.update();
        return ResponseEntity.ok("Cart has been modified");
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> remove(@PathVariable("itemId") String itemId) {
        cartService.remove(new ProductDto());
        return ResponseEntity.ok("Item has been removed from the cart");
    }
}