package com.gd.storeapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @GetMapping()
    public ResponseEntity<String> getOrderList() {
        return ResponseEntity.status(509).body("Not implemented");
    }

    @PostMapping()
    public ResponseEntity<String> createOrder() {
        return ResponseEntity.ok("Order has been created");
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable("orderId") String orderId) {
        return ResponseEntity.status(509).body("Not implemented");
    }
}