package com.pro_servises.pro.service;

import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.enums.Category;
import com.pro_servises.pro.model.Enterprise;
import com.pro_servises.pro.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

public interface ProductService {

    ProductDto addProductDto(ProductDto productDto, Integer enterpriseId , byte[] imageBytes);

    ProductDto getProductById(Integer productId);

    List<ProductDto> getAllProductsByEnterpriseId(Integer enterpriseId);

     void deleteProductById(Integer productId);

     ProductDto updateProduct(ProductDto productDto);

     List<ProductDto> getAllProduct();

     Enterprise getEnterpriseByProductId(Integer productId);

     List<Product> recommendByCategoryAndPrice(Category category, Double price);

     List<Product> filterProducts(Category category, Double minPrice, Double maxPrice, String name);

     Page<Product> getProducts(Pageable pageable);

    Double getAverageStarsForProduct(Integer productId);

    Map<String, Long> countProductsByStatus(Integer enterpriseId);

    List<Product> getProductsWithOrders();

    long countProductsByEnterprise(Long enterpriseId);



}
