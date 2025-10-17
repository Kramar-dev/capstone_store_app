package com.gd.storeapi.service;

import com.gd.storeapi.dto.OrderDto;
import com.gd.storeapi.mapper.OrderMapper;
import com.gd.storeapi.model.Cart;
import com.gd.storeapi.model.Order;
import com.gd.storeapi.model.User;
import com.gd.storeapi.repository.CartRepository;
import com.gd.storeapi.repository.OrderRepository;
import com.gd.storeapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,
                        CartRepository cartRepository,
                        UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public List<OrderDto> getAll() {
        String userId = TokenContext.getUserId(); // stateless way
        return orderRepository.findByUserId(userId)
                .stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    public void create() {
        String userId = TokenContext.getUserId();
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user " + userId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Order> orders = cart.getItems().stream().map(item -> {
            Order order = new Order();
            order.setUser(user);
            order.setProduct(item.getProduct());
            order.setQuantity(item.getQuantity());
            order.setTotalPrice(item.getProduct().getPrice() * item.getQuantity());
            return order;
        }).toList();

        //if orders is empty, throw exception

        orderRepository.saveAll(orders);
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    public void remove(String orderId) {
        orderRepository.deleteById(orderId);
    }

    public int updateTotals(String orderId, int quantity, Double totalPrice) {
        return orderRepository.updateTotals(orderId, quantity, totalPrice);
    }
}


