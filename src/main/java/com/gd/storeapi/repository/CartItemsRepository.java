package com.gd.storeapi.repository;

import com.gd.storeapi.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemsRepository extends JpaRepository<CartItem, String> {

    Optional<CartItem> findByCartId(String cartId);
}
