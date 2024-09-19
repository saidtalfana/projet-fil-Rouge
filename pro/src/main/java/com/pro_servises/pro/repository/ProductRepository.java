package com.pro_servises.pro.repository;

import com.pro_servises.pro.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    @Query(value = "SELECT * FROM Product WHERE enterprise_id = ?1", nativeQuery = true)
    List<Product> findAllProductsByEnterpriseId(Integer enterprise_id);

//    @Query(value="select * from Product where provider_id=?" , nativeQuery=true)
//   List<Product> findAllProductsByProviderId(Integer provider_id);

    Product findByName(String name);





}
