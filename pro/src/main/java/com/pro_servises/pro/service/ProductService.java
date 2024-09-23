package com.pro_servises.pro.service;

import com.pro_servises.pro.dto.OrderDto;
import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ProductDto addProductDto(ProductDto productDto, Integer enterprise_id , byte[] imageBytes);

    ProductDto getProductById(Integer product_id);

    List<ProductDto> getAllProductsByEnterpriseId(Integer enterprise_id);

     void deleteProductById(Integer productId);

     ProductDto updateProduct(ProductDto productDto);

     List<ProductDto> getAllProduct();

//    List<Product> searchProducts(Float price, String name, String category);



}
