package com.pro_servises.pro.controller;

import com.pro_servises.pro.dto.OrderDto;
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
    public OrderDto addOrder(@RequestBody OrderDto orderdto, @RequestParam Integer product_id , @RequestParam Integer user_id) {
        return orderServiceImpl.addOrder(orderdto,product_id, user_id);
    }

    @DeleteMapping("/delete_order/{order_id}")
    public void deleteOrder(@PathVariable("order_id") Integer order_id) {
        orderServiceImpl.deleteOrderById(order_id);
    }

    @GetMapping("/get_order/{order_id}")
    public OrderDto getOrder(@PathVariable("order_id") Integer order_id) {
        return orderServiceImpl.getOrderById(order_id);
    }
    @GetMapping("/gets_order_by_user_id")
    public List<OrderDto> getOrderByUserId(@RequestParam Integer user_id) {
        return orderServiceImpl.getAllOrdersByUserId(user_id);
    }


}
