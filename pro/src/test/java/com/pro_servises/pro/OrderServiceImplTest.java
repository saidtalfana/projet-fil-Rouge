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
import com.pro_servises.pro.serviceImp.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

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

    private OrderDto orderDto;
    private Order order;
    private Product product;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        orderDto = new OrderDto();
        orderDto.setOrderId(1);
        orderDto.setName("Said Talfana");
        orderDto.setAddress("123 Street");
        orderDto.setEmail("talfana@example.com");
        orderDto.setPhoneNumber("1234567890");
        orderDto.setCustomerRequest("Please deliver fast.");

        product = new Product(); // Assume you have a proper constructor/methods
        product.setProductId(1);
        product.setName("Sample Product");

        user = new User(); // Assume you have a proper constructor/methods
        user.setId(1);
        user.setEmail("talfana@example.com");

        order = new Order();
        order.setOrderId(1);
        order.setOrderDate(new Date(System.currentTimeMillis()));
        order.setOrderTime(new Time(System.currentTimeMillis()));
        order.setName("Said Talfana");
        order.setAddress("123 Street");
        order.setEmail("talfana@example.com");
        order.setPhoneNumber("1234567890");
        order.setCustomerRequest("Please deliver fast.");
        order.setProduct(product);
        order.setUser(user);
    }

    @Test
    void addOrder_shouldSaveOrderAndReturnDto() {
        when(orderMapper.mapToOrder(orderDto)).thenReturn(order);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(orderMapper.mapToOrderDto(any(Order.class))).thenReturn(orderDto);

        OrderDto result = orderService.addOrder(orderDto, 1, 1);

        assertNotNull(result);
        assertEquals(orderDto.getOrderId(), result.getOrderId());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void getOrderById_shouldReturnOrderDto() {
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        when(orderMapper.mapToOrderDto(any(Order.class))).thenReturn(orderDto);

        OrderDto result = orderService.getOrderById(1);

        assertNotNull(result);
        assertEquals(orderDto.getOrderId(), result.getOrderId());
    }

    @Test
    void getOrderById_shouldThrowNotFoundException() {
        when(orderRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> orderService.getOrderById(1));
    }

    @Test
    void deleteOrderById_shouldCallDeleteMethod() {
        doNothing().when(orderRepository).deleteById(1);

        orderService.deleteOrderById(1);

        verify(orderRepository, times(1)).deleteById(1);
    }

    @Test
    void getAllOrdersByUserId_shouldReturnListOfOrderDtos() {
        when(orderRepository.getAllOrdersByUserId(1)).thenReturn(Arrays.asList(order));
        when(orderMapper.mapToOrderDto(any(Order.class))).thenReturn(orderDto);

        List<OrderDto> result = orderService.getAllOrdersByUserId(1);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(orderDto.getOrderId(), result.get(0).getOrderId());
    }

    @Test
    void getAllOrders_shouldReturnAllOrders() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order));
        when(orderMapper.mapToOrderDto(any(Order.class))).thenReturn(orderDto);

        List<OrderDto> result = orderService.getAllOrders();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(orderDto.getOrderId(), result.get(0).getOrderId());
    }
}
