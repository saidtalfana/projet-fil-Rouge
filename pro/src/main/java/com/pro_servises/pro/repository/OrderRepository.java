package com.pro_servises.pro.repository;

import com.pro_servises.pro.dto.OrderDto;
import com.pro_servises.pro.model.Order;
import com.pro_servises.pro.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select * from customer_order where user_id=?1", nativeQuery=true)
    List<Order> getAllOrdersByUserId(Integer userId);

    @Query(value = "select * from customer_order where product_id=?1", nativeQuery=true)
    List<Order> getAllOrdersByProductId(Integer productId);

    @Query(value = "SELECT DISTINCT c.* FROM `customer_order` AS c " +
            "JOIN product AS p ON c.product_id = p.product_id " +
            "JOIN enterprise AS e ON p.enterprise_id = e.enterprise_id " +
            "WHERE e.enterprise_id = ?1",
            nativeQuery = true)
    List<Order> getAllOrdersByEnterpriseId(Integer enterpriseId);


    @Query("SELECT p.name, COUNT(o.orderId) " +
            "FROM Product p JOIN Order o ON p.productId = o.product.productId " +
            "WHERE p.enterprise.enterpriseId = :enterpriseId " +
            "GROUP BY p.name")
    List<Object[]> findProductOrdersCountByEnterpriseId(Long enterpriseId);


    List<Order> findOrderByUserId(Integer userId);

}
