package com.gd.storeapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gd.storeapi.validation.OnCreate;
import com.gd.storeapi.validation.OnDelete;
import com.gd.storeapi.validation.OnUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItemDto {

    private String id;

    private String cartId;

    @NotBlank(message = "Product ID is required.", groups = {OnCreate.class, OnUpdate.class, OnDelete.class})
    private String productId;

    @NotNull(message = "Quantity is required.", groups = {OnUpdate.class})
    @Min(value = 0, message = "Quantity must be at least 1.", groups = {OnUpdate.class})
    private Integer quantity;
}
