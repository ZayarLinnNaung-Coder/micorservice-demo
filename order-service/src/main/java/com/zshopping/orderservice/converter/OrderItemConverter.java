package com.zshopping.orderservice.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zshopping.orderservice.dto.OrderItemDto;
import com.zshopping.orderservice.model.OrderItem;

public class OrderItemConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static OrderItem convertDtoToEntity(OrderItemDto orderItemDto){
        return objectMapper.convertValue(orderItemDto, OrderItem.class);
    }

    public static OrderItemDto convertEntityToDto(OrderItem orderItem){
        return objectMapper.convertValue(orderItem, OrderItemDto.class);
    }
}
