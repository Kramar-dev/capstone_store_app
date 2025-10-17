package com.gd.storeapi.mapper;

import com.gd.storeapi.dto.ProductDto;
import com.gd.storeapi.model.Product;

import java.util.List;

public class ProductMapper {

    private ProductMapper() {
    }

    public static ProductDto toDto(Product productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getId());
        productDto.setTitle(productEntity.getTitle());
        productDto.setPrice(productEntity.getPrice());
        productDto.setDescription(productEntity.getDescription());
        productDto.setQuantity(productEntity.getQuantity());
        return productDto;
    }

    public static Product toEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        return product;
    }

    public static List<ProductDto> toDto(List<Product> productEntityList) {
        return productEntityList.stream().map(ProductMapper::toDto).toList();
    }
}