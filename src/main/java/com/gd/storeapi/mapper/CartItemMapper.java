package com.gd.storeapi.mapper;

import com.gd.storeapi.dto.CartItemDto;
import com.gd.storeapi.model.CartItem;

public class CartItemMapper {

    private CartItemMapper() {
    }

    public static CartItemDto toDto(CartItem cartItem) {
        CartItemDto dto = new CartItemDto();
        dto.setId(cartItem.getId());
        dto.setCartId(cartItem.getCart() != null ? cartItem.getCart().getId() : null);
        dto.setProductId(cartItem.getProduct() != null ? cartItem.getProduct().getId() : null);
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }

    public static CartItem toEntity(CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDto.getId());
        cartItem.setQuantity(cartItemDto.getQuantity());
        return cartItem;
    }
}
