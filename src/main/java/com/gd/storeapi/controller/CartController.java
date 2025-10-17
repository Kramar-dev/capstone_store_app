package com.gd.storeapi.controller;

import com.gd.storeapi.dto.CartItemDto;
import com.gd.storeapi.dto.CartResponse;
import com.gd.storeapi.dto.ProductsResponse;
import com.gd.storeapi.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/cart/items")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartResponse> getAll() {
        CartResponse response = new CartResponse();
        response.setItems(cartService.getUserCartItems());
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> add(@RequestBody CartItemDto cartItemDto) {
        cartService.add(cartItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(
            @PathVariable String itemId,
            @RequestBody CartItemDto cartItemDto
    ) {
        cartService.update(itemId, cartItemDto);
        return ResponseEntity.ok("Cart item updated successfully");
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> remove(@PathVariable String itemId) {
        cartService.remove(itemId);
        return ResponseEntity.ok("Item removed from the cart");
    }
}
