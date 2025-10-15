package com.gd.storeapi.service;

import com.gd.storeapi.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService {

    public List<OrderDto> getAll() {
        return List.of(new OrderDto());
    }

    public void create(OrderDto order) {

    }

    public void update(OrderDto order) {

    }
}
