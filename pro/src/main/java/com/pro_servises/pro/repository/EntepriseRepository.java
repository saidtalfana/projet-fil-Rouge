package com.pro_servises.pro.repository;

import com.pro_servises.pro.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Dictionary;
import java.util.List;

@Repository
public interface EntepriseRepository extends JpaRepository<Enterprise, Integer> {

    @Query(value = "SELECT * FROM Enterprise where provider_id = ?1" , nativeQuery = true)
    Enterprise findByProviderId(Integer provider_id);
}
