package com.pro_servises.pro.repository;

import com.pro_servises.pro.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EntepriseRepository extends JpaRepository<Enterprise, Integer> {

    @Query(value = "SELECT * FROM Enterprise where provider_id = ?1" , nativeQuery = true)
    Enterprise findByProviderId(Integer providerId);

    @Query(value = "SELECT e.* FROM Enterprise e JOIN Product p ON e.enterprise_id = p.enterprise_id WHERE p.product_id = ?1", nativeQuery = true)
    Enterprise findByProductId(Integer productId);


}
