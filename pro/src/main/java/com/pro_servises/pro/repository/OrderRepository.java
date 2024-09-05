package com.pro_servises.pro.repository;

import com.pro_servises.pro.model.Order;
import com.pro_servises.pro.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select * from Order where user_id=?", nativeQuery=true)
    List<Order> getAllOrdersByUserId(Integer userId);
}
