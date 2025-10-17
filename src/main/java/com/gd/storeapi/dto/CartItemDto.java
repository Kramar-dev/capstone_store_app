package com.gd.storeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private String id;
    private String cartId;
    private String productId;
    private int quantity;
}

