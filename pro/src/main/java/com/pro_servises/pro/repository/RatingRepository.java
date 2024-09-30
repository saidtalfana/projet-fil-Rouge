package com.pro_servises.pro.repository;

import com.pro_servises.pro.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query(value = "SELECT * FROM Rating where product_id = ?1" , nativeQuery = true)
    List<Rating> findByProductId(Integer productId);
}
