package com.gd.storeapi.service;

import com.gd.storeapi.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductsService {

    public List<ProductDto> getAll() {
        return List.of(new ProductDto());
    }
}
