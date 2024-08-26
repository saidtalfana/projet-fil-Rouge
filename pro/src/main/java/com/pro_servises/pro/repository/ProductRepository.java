package com.pro_servises.pro.repository;

import com.pro_servises.pro.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value="select * from Product where provider_id=?" , nativeQuery=true)
   List<Product> findAllProductsByProviderId(Long provider_id);
}
