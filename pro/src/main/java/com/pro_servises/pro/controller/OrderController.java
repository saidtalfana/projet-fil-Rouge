package com.pro_servises.pro.controller;

import com.pro_servises.pro.model.Order;
import com.pro_servises.pro.service.OrderService;
import com.pro_servises.pro.serviceImp.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {


    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @PostMapping("/add_order")
    public Order addOrder(@RequestBody Order order,@RequestParam Long product_id) {
        return orderServiceImpl.addOrder(order,product_id);
    }

    @DeleteMapping("/delete_order/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderServiceImpl.deleteOrder(id);
    }

    @GetMapping("/gets_order_by_provider_id")
    public List<Order> getOrderByProviderId(@RequestParam Long providerId) {
        return orderServiceImpl.getAllOrdersByProviderId(providerId);
    }
    @GetMapping("/gets_order_by_user_id")
    public List<Order> getOrderByUserId(@RequestParam Long userId) {
        return orderServiceImpl.getAllOrdersByUserId(userId);
    }


}
