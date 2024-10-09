package com.pro_servises.pro.controller;

import com.pro_servises.pro.dto.CommandDTO;
import com.pro_servises.pro.dto.OrderDto;
import com.pro_servises.pro.serviceImp.OrderServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {


    private final OrderServiceImpl orderServiceImpl;

    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @PostMapping("/add_order")
    public OrderDto addOrder(@RequestBody OrderDto orderdto, @RequestParam Integer productId , @RequestParam Integer userId) {
        return orderServiceImpl.addOrder(orderdto,productId, userId);
    }

    @DeleteMapping("/delete_order/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Integer orderId) {
        orderServiceImpl.deleteOrderById(orderId);
    }

    @GetMapping("/get_order/{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Integer orderId) {
        return orderServiceImpl.getOrderById(orderId);
    }
    @GetMapping("/gets_order_by_user_id/{userId}")
    public List<OrderDto> getOrderByUserId(@PathVariable Integer userId) {
        return orderServiceImpl.getAllOrdersByUserId(userId);
    }

    @GetMapping("/gets_order_by_product_id/{productId}")
    public List<OrderDto> getOrderByProductId(@PathVariable Integer productId) {
        return orderServiceImpl.getAllOrdersByProductId(productId);
    }

    @GetMapping("/get_all_order_by_enterprise_id/{enterpriseId}")
    public List<OrderDto> getAllOrderByEnterpriseId(@PathVariable Integer enterpriseId) {
        return orderServiceImpl.getAllOrdersByEnterpriseId(enterpriseId);
    }
    @GetMapping("/get_all_orders")
    public List<OrderDto> getAllOrders() {
        return orderServiceImpl.getAllOrders();
    }

        @PutMapping("/update-status/{orderId}")
        public ResponseEntity<String> updateOrderStatusToDone(@PathVariable Integer orderId) {
            try {
                orderServiceImpl.updateOrderStatusToDone(orderId);
                return ResponseEntity.ok("Order status updated to 'done', and rating prompt email sent to the customer.");
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

    @GetMapping("/count/{enterpriseId}")
    public List<Object[]> getProductOrdersCount(@PathVariable Long enterpriseId) {
        return orderServiceImpl.getProductOrdersCountByEnterpriseId(enterpriseId);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommandDTO>> getUserOrders(@PathVariable Integer userId) {
        List<CommandDTO> orders = orderServiceImpl.getUserOrders(userId);
        return ResponseEntity.ok(orders); // Retourne la liste des commandes dans la réponse
    }
}
