package com.pro_servises.pro;

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
import com.pro_servises.pro.serviceImp.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderMapper orderMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOrder() {
        // Given
        OrderDto orderDto = new OrderDto();
        Order order = new Order();
        Product product = new Product();
        User user = new User();
        Order savedOrder = new Order();
        savedOrder.setOrderId(1);
        OrderDto savedOrderDto = new OrderDto();

        when(orderMapper.mapToOrder(orderDto)).thenReturn(order);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(orderRepository.save(order)).thenReturn(savedOrder);
        when(orderMapper.mapToOrderDto(savedOrder)).thenReturn(savedOrderDto);

        // When
        OrderDto result = orderService.addOrder(orderDto, 1, 1);

        // Then
        assertNotNull(result);
        assertEquals(savedOrderDto, result);
        verify(orderRepository, times(1)).save(order);
        verify(productRepository, times(1)).findById(1);
        verify(userRepository, times(1)).findById(1);
        verify(orderMapper, times(1)).mapToOrder(orderDto);
        verify(orderMapper, times(1)).mapToOrderDto(savedOrder);
    }

    @Test
    void testDeleteOrderById() {
        // When
        orderService.deleteOrderById(1);

        // Then
        verify(orderRepository, times(1)).deleteById(1);
    }

    @Test
    void testGetOrderById() {
        // Given
        Integer orderId = 1;
        Order order = new Order();
        OrderDto orderDto = new OrderDto();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderMapper.mapToOrderDto(order)).thenReturn(orderDto);

        // When
        OrderDto result = orderService.getOrderById(orderId);

        // Then
        assertNotNull(result);
        assertEquals(orderDto, result);
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderMapper, times(1)).mapToOrderDto(order);
    }

    @Test
    void testGetAllOrdersByUserId() {
        // Given
        Integer userId = 1;
        Order order = new Order();
        OrderDto orderDto = new OrderDto();
        List<Order> orders = Collections.singletonList(order);
        List<OrderDto> orderDtos = Collections.singletonList(orderDto);

        when(orderRepository.getAllOrdersByUserId(userId)).thenReturn(orders);
        when(orderMapper.mapToOrderDto(order)).thenReturn(orderDto);

        // When
        List<OrderDto> result = orderService.getAllOrdersByUserId(userId);

        // Then
        assertNotNull(result);
        assertEquals(orderDtos.size(), result.size());
        assertEquals(orderDtos.get(0), result.get(0));
        verify(orderRepository, times(1)).getAllOrdersByUserId(userId);
        verify(orderMapper, times(1)).mapToOrderDto(order);
    }

    @Test
    void testGetAllOrdersByProductId() {
        // Given
        Integer productId = 1;
        Order order = new Order();
        OrderDto orderDto = new OrderDto();
        List<Order> orders = Collections.singletonList(order);
        List<OrderDto> orderDtos = Collections.singletonList(orderDto);

        when(orderRepository.getAllOrdersByProductId(productId)).thenReturn(orders);
        when(orderMapper.mapToOrderDto(order)).thenReturn(orderDto);

        // When
        List<OrderDto> result = orderService.getAllOrdersByProductId(productId);

        // Then
        assertNotNull(result);
        assertEquals(orderDtos.size(), result.size());
        assertEquals(orderDtos.get(0), result.get(0));
        verify(orderRepository, times(1)).getAllOrdersByProductId(productId);
        verify(orderMapper, times(1)).mapToOrderDto(order);
    }

    @Test
    void testGetAllOrdersByEnterpriseId() {
        // Given
        Integer enterpriseId = 1;
        Order order = new Order();
        OrderDto orderDto = new OrderDto();
        List<Order> orders = Collections.singletonList(order);
        List<OrderDto> orderDtos = Collections.singletonList(orderDto);

        when(orderRepository.getAllOrdersByEnterpriseId(enterpriseId)).thenReturn(orders);
        when(orderMapper.mapToOrderDto(order)).thenReturn(orderDto);

        // When
        List<OrderDto> result = orderService.getAllOrdersByEnterpriseId(enterpriseId);

        // Then
        assertNotNull(result);
        assertEquals(orderDtos.size(), result.size());
        assertEquals(orderDtos.get(0), result.get(0));
        verify(orderRepository, times(1)).getAllOrdersByEnterpriseId(enterpriseId);
        verify(orderMapper, times(1)).mapToOrderDto(order);
    }

    @Test
    void testGetAllOrders() {
        // Given
        Order order = new Order();
        OrderDto orderDto = new OrderDto();
        List<Order> orders = Collections.singletonList(order);
        List<OrderDto> orderDtos = Collections.singletonList(orderDto);

        when(orderRepository.findAll()).thenReturn(orders);
        when(orderMapper.mapToOrderDto(order)).thenReturn(orderDto);

        // When
        List<OrderDto> result = orderService.getAllOrders();

        // Then
        assertNotNull(result);
        assertEquals(orderDtos.size(), result.size());
        assertEquals(orderDtos.get(0), result.get(0));
        verify(orderRepository, times(1)).findAll();
        verify(orderMapper, times(1)).mapToOrderDto(order);
    }
}
