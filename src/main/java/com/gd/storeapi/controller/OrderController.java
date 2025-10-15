package com.gd.storeapi.controller;

import com.gd.storeapi.dto.OrderDto;
import com.gd.storeapi.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll() {
        List<OrderDto> orders = orderService.getAll();
        return ResponseEntity.status(501).body("Not implemented");
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create() {
        orderService.create(new OrderDto());
        return ResponseEntity.ok("Order has been created");
    }

    @PutMapping(path = "/{orderId}/cancel", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> cancel(@PathVariable("orderId") String orderId) {
        orderService.update(new OrderDto());
        return ResponseEntity.status(501).body("Not implemented");
    }
}