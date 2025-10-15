package com.gd.storeapi.controller;

import com.gd.storeapi.dto.OrderDto;
import com.gd.storeapi.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    private final OrderService orderService;

    @GetMapping()
    public ResponseEntity<String> getAll() {
        List<OrderDto> orders = orderService.getAll();
        return ResponseEntity.status(509).body("Not implemented");
    }

    @PostMapping()
    public ResponseEntity<String> create() {
        orderService.create(new OrderDto());
        return ResponseEntity.ok("Order has been created");
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancel(@PathVariable("orderId") String orderId) {
        orderService.update(new OrderDto());
        return ResponseEntity.status(509).body("Not implemented");
    }
}