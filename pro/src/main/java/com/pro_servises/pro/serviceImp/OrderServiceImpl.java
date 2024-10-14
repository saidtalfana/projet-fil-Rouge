package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.CommandDTO;
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
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link OrderService} interface.
 *
 * This service class is responsible for handling operations related to orders,
 * including adding, retrieving, updating, and deleting orders.
 * It interacts with the {@link OrderRepository}, {@link UserRepository},
 * and {@link ProductRepository} for database operations and uses
 * {@link OrderMapper} for mapping between Order and OrderDto.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    /**
     * Constructor for OrderServiceImpl.
     *
     * @param orderRepository the repository for managing order data
     * @param userRepository the repository for managing user data
     * @param productRepository the repository for managing product data
     * @param orderMapper the mapper for converting between Order and OrderDto
     */
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository,
                            ProductRepository productRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }

    /**
     * Adds a new order to the system.
     *
     * @param orderDto the data transfer object containing order details
     * @param productId the ID of the product being ordered
     * @param userId the ID of the user placing the order
     * @return the saved order data transfer object
     * @throws NotFoundException if the product or user is not found
     */
    @Override
    public OrderDto addOrder(OrderDto orderDto, Integer productId, Integer userId) {
        Order order = orderMapper.mapToOrder(orderDto);
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("id " + productId + " not found"));
        order.setProduct(product);

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("id " + userId + " not found"));

        order.setUser(user);
        order.setOrderDate(new Date(System.currentTimeMillis()));
        order.setOrderTime(new Time(System.currentTimeMillis()));

        Order savedOrder = orderRepository.save(order);
        return orderMapper.mapToOrderDto(savedOrder);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param orderId the ID of the order to delete
     */
    @Override
    public void deleteOrderById(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId the ID of the order to retrieve
     * @return the order data transfer object
     * @throws NotFoundException if the order is not found
     */
    @Override
    public OrderDto getOrderById(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException("id " + orderId + " not found"));
        return orderMapper.mapToOrderDto(order);
    }

    /**
     * Retrieves all orders placed by a specific user.
     *
     * @param userId the ID of the user whose orders are to be retrieved
     * @return a list of order data transfer objects
     */
    @Override
    public List<OrderDto> getAllOrdersByUserId(Integer userId) {
        List<Order> orders = orderRepository.getAllOrdersByUserId(userId);
        return orders.stream()
                .map(orderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all orders for a specific product.
     *
     * @param productId the ID of the product whose orders are to be retrieved
     * @return a list of order data transfer objects
     */
    @Override
    public List<OrderDto> getAllOrdersByProductId(Integer productId) {
        List<Order> orders = orderRepository.getAllOrdersByProductId(productId);
        return orders.stream()
                .map(orderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all orders for a specific enterprise.
     *
     * @param enterpriseId the ID of the enterprise whose orders are to be retrieved
     * @return a list of order data transfer objects
     */
    @Override
    public List<OrderDto> getAllOrdersByEnterpriseId(Integer enterpriseId) {
        List<Order> orders = orderRepository.getAllOrdersByEnterpriseId(enterpriseId);
        return orders.stream()
                .map(orderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all orders in the system.
     *
     * @return a list of all order data transfer objects
     */
    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }

    /**
     * Updates the status of an order to "Done".
     *
     * @param orderId the ID of the order to update
     * @throws NotFoundException if the order is not found
     */
    @Override
    public void updateOrderStatusToDone(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found"));

        orderRepository.save(order);
    }

    /**
     * Retrieves the count of product orders for a specific enterprise.
     *
     * @param enterpriseId the ID of the enterprise
     * @return a list of objects containing product order counts
     */
    @Override
    public List<Object[]> getProductOrdersCountByEnterpriseId(Long enterpriseId) {
        return orderRepository.findProductOrdersCountByEnterpriseId(enterpriseId);
    }

    /**
     * Retrieves a list of CommandDTOs for the given user ID.
     *
     * @param userId the ID of the user
     * @return a list of CommandDTO objects
     */
    public List<CommandDTO> getUserOrders(Integer userId) {
        // Fetch the orders by user ID
        List<Order> orders = orderRepository.findOrderByUserId(userId);

        // Map orders to CommandDTOs
        return orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Converts an Order object to a CommandDTO object.
     *
     * @param order the Order object to convert
     * @return the corresponding CommandDTO object
     */
    private CommandDTO convertToDTO(Order order) {
        Product product = order.getProduct(); // Assuming Order has a getProduct() method

        return new CommandDTO(
                order.getOrderId() != null ? order.getOrderId().toString() : null, // Adjust based on your Order entity
                product != null ? product.getName() : null,
                product != null ? product.getPrice() : null,
                order.getOrderDate() != null ? order.getOrderDate().toString() : null, // Adjust based on your Order entity
                order.getAddress(),
                order.getEmail() // Assuming Order has an email field
        );
    }
}
