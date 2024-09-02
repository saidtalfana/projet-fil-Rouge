package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.OrderDto;
import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.exception.ConflictException;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.model.Order;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.model.Provider;
import com.pro_servises.pro.repository.OrderRepository;
import com.pro_servises.pro.repository.ProductRepository;
import com.pro_servises.pro.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;


















    public OrderDto addOrder(OrderDto orderDto, Integer product_id) {
        Product product = productRepository.findById(product_id).get();
        order.setProduct(product);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);

    }

    @Override
    public List<Order> getAllOrdersByProviderId(Integer providerId) {
        return orderRepository.getAllOrdersByProviderId(providerId);
    }

    @Override
    public List<Order> getAllOrdersByUserId(Integer userId) {
        return orderRepository.getAllOrdersByUserId(userId);
    }
}
