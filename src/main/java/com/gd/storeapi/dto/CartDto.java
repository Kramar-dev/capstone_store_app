package com.gd.storeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private String id;
    private String userId;
    private String productId;
    private int quantity;
    private Double totalPrice;
}
