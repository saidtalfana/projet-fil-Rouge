package com.pro_servises.pro.service;

import com.pro_servises.pro.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto addOrder(OrderDto orderDto, Integer productId , Integer userId);

    void deleteOrderById(Integer orderId);

    OrderDto getOrderById(Integer orderId);

    List<OrderDto> getAllOrdersByUserId(Integer userId);

    List<OrderDto> getAllOrdersByProductId(Integer productId);

     List<OrderDto> getAllOrdersByEnterpriseId(Integer enterpriseId);

    List<OrderDto> getAllOrders();

    void updateOrderStatusToDone(Integer orderId);

}
