package com.gd.storeapi.service;

import com.gd.storeapi.dto.ProductDto;
import com.gd.storeapi.mapper.ProductMapper;
import com.gd.storeapi.model.Product;
import com.gd.storeapi.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductsService {

    private final ProductRepository productRepository;

    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        log.info("Fetched {} products from database", products.size());
        return ProductMapper.toDto(products);
    }
}
