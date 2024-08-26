package com.pro_servises.pro.repository;

import com.pro_servises.pro.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select * from Order where provider_id=?", nativeQuery=true)
    List<Order> getAllOrdersByProviderId(Long providerId);

    @Query(value = "select * from Order where user_id=?", nativeQuery=true)
    List<Order> getAllOrdersByUserId(Long userId);
}
