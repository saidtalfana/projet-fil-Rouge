package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.CommandDTO;
import com.pro_servises.pro.dto.OrderDto;
import com.pro_servises.pro.enums.OrderStatus;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.mapper.OrderMapper;
import com.pro_servises.pro.model.Order;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.model.User;
import com.pro_servises.pro.repository.OrderRepository;
import com.pro_servises.pro.repository.ProductRepository;
import com.pro_servises.pro.repository.UserRepository;
import com.pro_servises.pro.service.OrderService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final OrderMapper orderMapper;


    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }

    @Override
   public OrderDto addOrder(OrderDto orderDto, Integer productId , Integer userId) {
        Order order = orderMapper.mapToOrder(orderDto);
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("id " + productId + " not found"));
        order.setProduct(product);
        order.setOrderStatus(OrderStatus.PENDING);

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("id " + userId + " not found"));

        order.setUser(user);
        order.setOrderDate(new Date(System.currentTimeMillis()));
        order.setOrderTime(new Time(System.currentTimeMillis()));

        Order savedOrder = orderRepository.save(order);
        return orderMapper.mapToOrderDto(savedOrder);
    }

    @Override
   public void deleteOrderById(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
   public OrderDto getOrderById(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException("id " + orderId + " not found"));
        return orderMapper.mapToOrderDto(order);
    }

    @Override
   public List<OrderDto> getAllOrdersByUserId(Integer userId) {
        List<Order> orders = orderRepository.getAllOrdersByUserId(userId);
        return orders.stream()
                .map(orderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAllOrdersByProductId(Integer productId) {
        List<Order> orders = orderRepository.getAllOrdersByProductId(productId);
        return orders.stream()
                .map(orderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<OrderDto> getAllOrdersByEnterpriseId(Integer enterpriseId) {
        List<Order> orders = orderRepository.getAllOrdersByEnterpriseId(enterpriseId);

        return orders.stream()
                .map(orderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::mapToOrderDto)
                .collect(Collectors.toList());

    }

    @Override
    public void updateOrderStatusToDone(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        // Update order status
        order.setOrderStatus(OrderStatus.DONE);
        orderRepository.save(order);

    }

    public List<Object[]> getProductOrdersCountByEnterpriseId(Long enterpriseId) {
        return orderRepository.findProductOrdersCountByEnterpriseId(enterpriseId);
    }



    public List<CommandDTO> getUserOrders(Integer userId) {
        List<Order> orders = orderRepository.findOrderByUserId(userId); // Récupérer les commandes de l'utilisateur
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CommandDTO convertToDTO(Order order) {
        // Vérifiez que le produit n'est pas nul
        Product product = order.getProduct(); // Récupérer le produit depuis la commande

        return new CommandDTO(
                order.getOrderId() != null ? order.getOrderId().toString() : null, // ID de la commande converti en String
                product != null ? product.getName() : null, // Nom du produit
                product != null ? product.getPrice() : null, // Prix du produit
                order.getOrderDate() != null ? order.getOrderDate().toString() : null, // Date de la commande
                order.getAddress(), // Adresse de livraison
                order.getEmail()    // Email du client
        );
    }

}

















