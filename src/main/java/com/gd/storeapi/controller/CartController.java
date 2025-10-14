package com.gd.storeapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/cart/items")
public class CartController {

    @GetMapping()
    public ResponseEntity<String> getItems() {
        return ResponseEntity.ok("List of items in the cart");
    }

    @PostMapping()
    public ResponseEntity<String> addItem() {
        return ResponseEntity.ok("Item has been added to the cart");
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<String> modifyCart(@PathVariable("itemId") String itemId) {
        return ResponseEntity.ok("Cart has been modified");
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> removeItem(@PathVariable("itemId") String itemId) {
        return ResponseEntity.ok("Item has been removed from the cart");
    }
}