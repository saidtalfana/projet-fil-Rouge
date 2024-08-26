package com.pro_servises.pro.service;

import com.pro_servises.pro.model.Order;

import java.util.List;

public interface OrderService {

    Order addOrder(Order order,Long product_id);

    void deleteOrder(Long orderId);

    List<Order> getAllOrdersByProviderId(Long providerId);

    List<Order> getAllOrdersByUserId(Long userId);
}
