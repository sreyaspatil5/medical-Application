package com.smart.application.services;

import com.smart.application.models.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderById(Long orderId);

    Order updateOrder(Long orderId, Order order);

    void deleteOrder(Long orderId);
}
