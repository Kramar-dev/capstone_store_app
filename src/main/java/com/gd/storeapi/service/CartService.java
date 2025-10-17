package com.gd.storeapi.service;

import com.gd.storeapi.dto.CartItemDto;
import com.gd.storeapi.mapper.CartItemMapper;
import com.gd.storeapi.model.Cart;
import com.gd.storeapi.model.CartItem;
import com.gd.storeapi.model.Product;
import com.gd.storeapi.repository.CartItemsRepository;
import com.gd.storeapi.repository.CartRepository;
import com.gd.storeapi.repository.ProductRepository;
import com.gd.storeapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemsRepository cartItemsRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository,
                       CartItemsRepository cartItemsRepository,
                       ProductRepository productRepository,
                       UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartItemsRepository = cartItemsRepository;
    }

    public List<CartItemDto> getUserCartItems() {
        String userId = TokenContext.getUserId();

        return cartRepository.findByUserId(userId)
                .map(cart -> cart.getItems().stream()
                        .map(CartItemMapper::toDto)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public void add(CartItemDto cartItemDto) {
        String userId = TokenContext.getUserId();

        if (cartItemDto.getQuantity() == 0) {
            cartItemDto.setQuantity(1);
        }

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(userRepository.findById(userId)
                            .orElseThrow(() -> new IllegalArgumentException("User not found")));
                    return cartRepository.save(newCart);
                });

        Product product = productRepository.findById(cartItemDto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        CartItem cartItem = CartItemMapper.toEntity(cartItemDto);
        cartItem.setCart(cart);
        cartItem.setProduct(product);

        cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .ifPresentOrElse(
                        existingItem -> existingItem.setQuantity(existingItem.getQuantity() + cartItem.getQuantity()),
                        () -> cart.getItems().add(cartItem)
                );

        cartRepository.save(cart);
    }

    public void update(CartItemDto cartItemDto) {
        String userId = TokenContext.getUserId();

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        CartItem item = cartItemsRepository.findByCartId(cart.getId())
                .orElseThrow(() -> new IllegalArgumentException("Item not found in cart"));

        item.setQuantity(cartItemDto.getQuantity());
        cartRepository.save(cart);
    }

    public void remove(CartItemDto cartItemDto) {
        String userId = TokenContext.getUserId();

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        cart.getItems().removeIf(item -> item.getProduct().getId().equals(cartItemDto.getProductId()));
        cartRepository.save(cart);
    }
}