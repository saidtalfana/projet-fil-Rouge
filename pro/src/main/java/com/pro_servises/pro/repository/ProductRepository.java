package com.pro_servises.pro.repository;

import com.pro_servises.pro.dto.ProductReportDTO;
import com.pro_servises.pro.enums.Category;
import com.pro_servises.pro.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {


    @Query(value = "SELECT * FROM Product WHERE enterprise_id = ?1", nativeQuery = true)
    List<Product> findAllProductsByEnterpriseId(Integer enterpriseId);



    Product findByName(String name);


    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.price = :price")
    List<Product> findByCategoryAndPrice(@Param("category") Category category, @Param("price") Double price);
    @Query(value = "SELECT COUNT(*) FROM Product WHERE enterprise_id = ?1", nativeQuery = true)
    long countProductsByEnterprise(Long enterpriseId);

    @Query(value = "SELECT * FROM Product p WHERE EXISTS (SELECT 1 FROM Order o WHERE o.product_id = p.id)", nativeQuery = true)
    List<Product> findProductsWithOrders();

    @Query("SELECT p.productStatus, COUNT(p) FROM Product p WHERE p.enterprise.enterpriseId = :enterpriseId GROUP BY p.productStatus")
    List<Object[]> countProductsByStatus(@Param("enterpriseId") Integer enterpriseId);


//    @Query("SELECT new com.pro_servises.pro.dto.ProductReportDTO(p.name, p.description, p.price, CAST(p.category AS string), CAST(p.productStatus AS string), COUNT(o)) " +
//            "FROM Product p LEFT JOIN p.order o " +
//            "WHERE p.enterprise.enterpriseId = :enterpriseId " +
//            "GROUP BY p.name, p.description, p.price, p.category, p.productStatus")
//    List<ProductReportDTO> getProductReport(@Param("enterpriseId") Long enterpriseId);
@Query("SELECT new com.pro_servises.pro.dto.ProductReportDTO(p.name, p.description, p.price, " +
        "CAST(p.category AS string), CAST(p.productStatus AS string), COUNT(o)) " +
        "FROM Product p LEFT JOIN p.orders o " +  // Changed 'p.order' to 'p.orders'
        "WHERE p.enterprise.enterpriseId = :enterpriseId " +
        "GROUP BY p.name, p.description, p.price, p.category, p.productStatus")
List<ProductReportDTO> getProductReport(@Param("enterpriseId") Long enterpriseId);


    @Query("SELECT AVG(r.stars) FROM Rating r WHERE r.product.productId = :productId")
    Double getAverageStarsForProduct(Integer productId);

}
