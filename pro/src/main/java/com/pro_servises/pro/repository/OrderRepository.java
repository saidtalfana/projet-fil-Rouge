package com.pro_servises.pro.repository;

import com.pro_servises.pro.model.Order;
import com.pro_servises.pro.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select * from Order where user_id=?1", nativeQuery=true)
    List<Order> getAllOrdersByUserId(Integer user_id);

    @Query(value = "select * from Order where product_id=?1", nativeQuery=true)
    List<Order> getAllOrdersByProductId(Integer productId);

    @Query(value = "SELECT * FROM Product WHERE provider_id = ?1", nativeQuery = true)
    List<Order> getAllOrders();
}
