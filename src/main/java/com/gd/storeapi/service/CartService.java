package com.gd.storeapi.service;

import com.gd.storeapi.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartService {

    public List<ProductDto> getAll() {
        return List.of(new ProductDto());
    }

    public void add(ProductDto product) {

    }

    public void update() {

    }

    public void remove(ProductDto product) {

    }

}
