package com.gd.storeapi.repository;

import com.gd.storeapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.quantity = :quantity, o.totalPrice = :totalPrice WHERE o.id = :id")
    int updateTotals(@Param("id") String id,
                     @Param("quantity") int quantity,
                     @Param("totalPrice") Double totalPrice);

    List<Order> findByUserId(String userId);
}

