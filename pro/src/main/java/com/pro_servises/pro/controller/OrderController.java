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

    /**
     * Add a new order
     *
     * @param orderdto the order data to be added
     * @param productId the ID of the product being ordered
     * @param userId the ID of the user placing the order
     * @return the created OrderDto object
     */
    @PostMapping("/add_order")
    public OrderDto addOrder(@RequestBody OrderDto orderdto, @RequestParam Integer productId, @RequestParam Integer userId) {
        return orderServiceImpl.addOrder(orderdto, productId, userId);
    }

    /**
     * Delete an order by ID
     *
     * @param orderId the ID of the order to be deleted
     */
    @DeleteMapping("/delete_order/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Integer orderId) {
        orderServiceImpl.deleteOrderById(orderId);
    }

    /**
     * Get an order by ID
     *
     * @param orderId the ID of the order
     * @return the corresponding OrderDto object
     */
    @GetMapping("/get_order/{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Integer orderId) {
        return orderServiceImpl.getOrderById(orderId);
    }

    /**
     * Get all orders placed by a specific user
     *
     * @param userId the ID of the user
     * @return a list of OrderDto objects for the user's orders
     */
    @GetMapping("/gets_order_by_user_id/{userId}")
    public List<OrderDto> getOrderByUserId(@PathVariable Integer userId) {
        return orderServiceImpl.getAllOrdersByUserId(userId);
    }

    /**
     * Get all orders for a specific product
     *
     * @param productId the ID of the product
     * @return a list of OrderDto objects for the product's orders
     */
    @GetMapping("/gets_order_by_product_id/{productId}")
    public List<OrderDto> getOrderByProductId(@PathVariable Integer productId) {
        return orderServiceImpl.getAllOrdersByProductId(productId);
    }

    /**
     * Get all orders placed through a specific enterprise
     *
     * @param enterpriseId the ID of the enterprise
     * @return a list of OrderDto objects for the enterprise's orders
     */
    @GetMapping("/get_all_order_by_enterprise_id/{enterpriseId}")
    public List<OrderDto> getAllOrderByEnterpriseId(@PathVariable Integer enterpriseId) {
        return orderServiceImpl.getAllOrdersByEnterpriseId(enterpriseId);
    }

    /**
     * Get all orders
     *
     * @return a list of all OrderDto objects
     */
    @GetMapping("/get_all_orders")
    public List<OrderDto> getAllOrders() {
        return orderServiceImpl.getAllOrders();
    }

    /**
     * Update the status of an order to "done"
     *
     * @param orderId the ID of the order to be updated
     * @return a ResponseEntity with a success or error message
     */
    @PutMapping("/update-status/{orderId}")
    public ResponseEntity<String> updateOrderStatusToDone(@PathVariable Integer orderId) {
        try {
            orderServiceImpl.updateOrderStatusToDone(orderId);
            return ResponseEntity.ok("Order status updated to 'done', and rating prompt email sent to the customer.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Get the count of orders for each product in a specific enterprise
     *
     * @param enterpriseId the ID of the enterprise
     * @return a list of Object arrays containing product order counts
     */
    @GetMapping("/count/{enterpriseId}")
    public List<Object[]> getProductOrdersCount(@PathVariable Long enterpriseId) {
        return orderServiceImpl.getProductOrdersCountByEnterpriseId(enterpriseId);
    }

    /**
     * Get all orders placed by a specific user
     *
     * @param userId the ID of the user
     * @return a ResponseEntity containing a list of CommandDTO objects for the user's orders
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommandDTO>> getUserOrders(@PathVariable Integer userId) {
        List<CommandDTO> orders = orderServiceImpl.getUserOrders(userId);
        return ResponseEntity.ok(orders);
    }
}
