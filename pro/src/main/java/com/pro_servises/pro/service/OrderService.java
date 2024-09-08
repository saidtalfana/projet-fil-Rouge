package com.pro_servises.pro.service;

import com.pro_servises.pro.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto addOrder(OrderDto orderDto, Integer product_id , Integer user_id);

    void deleteOrderById(Integer order_id);

    OrderDto getOrderById(Integer order_id);

    List<OrderDto> getAllOrdersByUserId(Integer user_id);

    List<OrderDto> getAllOrdersByProductId(Integer product_id);

    List<OrderDto> getAllOrders();

}
