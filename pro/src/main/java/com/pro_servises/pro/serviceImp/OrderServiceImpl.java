package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.OrderDto;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.mapper.OrderMapper;
import com.pro_servises.pro.model.Order;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.model.User;
import com.pro_servises.pro.repository.OrderRepository;
import com.pro_servises.pro.repository.ProductRepository;
import com.pro_servises.pro.repository.UserRepository;
import com.pro_servises.pro.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
   public OrderDto addOrder(OrderDto orderDto, Integer product_id , Integer user_id) {
        Order order = orderMapper.mapToOrder(orderDto);
        Product product = productRepository.findById(product_id).orElseThrow(
                () -> new NotFoundException("id " + product_id + " not found"));
        order.setProduct(product);

        User user = userRepository.findById(user_id).orElseThrow(
                () -> new NotFoundException("id " + user_id + " not found"));

        order.setUser(user);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.mapToOrderDto(savedOrder);
    }

    @Override
   public void deleteOrderById(Integer order_id) {
        orderRepository.deleteById(order_id);
    }

    @Override
   public OrderDto getOrderById(Integer order_id) {
        Order order = orderRepository.findById(order_id).orElseThrow(
                () -> new NotFoundException("id " + order_id + " not found"));
        return orderMapper.mapToOrderDto(order);
    }

    @Override
   public List<OrderDto> getAllOrdersByUserId(Integer user_id) {
        List<Order> orders = orderRepository.getAllOrdersByUserId(user_id);

        return orders.stream()
                .map(orderMapper::mapToOrderDto)
                .collect(Collectors.toList());

    }

}

















