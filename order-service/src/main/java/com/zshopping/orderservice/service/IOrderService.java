package com.zshopping.orderservice.service;

import com.zshopping.orderservice.dto.OrderRequest;
import com.zshopping.orderservice.model.Order;

import java.util.List;

public interface IOrderService {
    String placeOrder(OrderRequest orderRequest);
    List<Order> findAllOrders();
}
