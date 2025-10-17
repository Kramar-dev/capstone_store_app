package com.gd.storeapi.mapper;

import com.gd.storeapi.dto.ProductDto;
import com.gd.storeapi.model.Product;

import java.util.List;

public class CartMapper {

    private CartMapper() {
    }

    public static List<ProductDto> toDto(List<Product> products) {
        return products.stream().map(ProductMapper::toDto).toList();
    }

    public static List<Product> toEntity(List<ProductDto> productDtos) {
        return productDtos.stream().map(ProductMapper::toEntity).toList();
    }
}