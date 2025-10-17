package com.gd.storeapi.mapper;

import com.gd.storeapi.dto.OrderDto;
import com.gd.storeapi.model.Order;

public class OrderMapper {

    private OrderMapper() {}

    public static OrderDto toDto(Order orderEntity) {
        OrderDto dto = new OrderDto();
        dto.setId(orderEntity.getId());
        dto.setUserId(orderEntity.getUser() != null ? orderEntity.getUser().getId() : null);
        dto.setProductId(orderEntity.getProduct() != null ? orderEntity.getProduct().getId() : null);
        dto.setQuantity(orderEntity.getQuantity());
        dto.setTotalPrice(orderEntity.getTotalPrice());
        return dto;
    }

    public static Order toEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setQuantity(orderDto.getQuantity());
        order.setTotalPrice(orderDto.getTotalPrice());
        return order;
    }
}
