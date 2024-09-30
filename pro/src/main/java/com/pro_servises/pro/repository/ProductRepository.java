package com.pro_servises.pro.repository;

import com.pro_servises.pro.enums.Category;
import com.pro_servises.pro.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {


    @Query(value = "SELECT * FROM Product WHERE enterprise_id = ?1", nativeQuery = true)
    List<Product> findAllProductsByEnterpriseId(Integer enterprise_id);

//    @Query(value="select * from Product where provider_id=?" , nativeQuery=true)
//   List<Product> findAllProductsByProviderId(Integer provider_id);

    Product findByName(String name);


    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.price = :price")
    List<Product> findByCategoryAndPrice(@Param("category") Category category, @Param("price") Double price);

//    List<Product> findByCategory(String category);
//    List<Product> findByProductName(String name);
//    List<Product> findByProductPrice(Float price);
//
//    List<Product> findByPriceAndCategory(Float price, String category);
//    List<Product> findByNameAndCategory(String name, String category);
//    List<Product> findByNameAndPrice(String name, Float price);
//    List<Product> findByNameAndPriceAndCategory(String name, Float price, String category);



}
