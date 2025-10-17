package com.gd.storeapi.controller;

import com.gd.storeapi.dto.CartItemDto;
import com.gd.storeapi.dto.CartResponse;
import com.gd.storeapi.service.CartService;
import com.gd.storeapi.validation.OnCreate;
import com.gd.storeapi.validation.OnDelete;
import com.gd.storeapi.validation.OnUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> add(
            @Validated(OnCreate.class) @RequestBody CartItemDto cartItemDto
    ) {
        cartService.add(cartItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(
            @Validated(OnUpdate.class) @RequestBody CartItemDto cartItemDto
    ) {
        cartService.update(cartItemDto);
        return ResponseEntity.ok("Cart item updated successfully");
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> remove(
            @Validated(OnDelete.class) @RequestBody CartItemDto cartItemDto
    ) {
        cartService.remove(cartItemDto);
        return ResponseEntity.ok("Item removed from the cart");
    }
}
