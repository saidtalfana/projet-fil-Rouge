package com.pro_servises.pro.service;

import com.pro_servises.pro.dto.OrderDto;
import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.model.Product;

import java.util.List;

public interface ProductService {

    ProductDto addProductDto(ProductDto productDto, Integer enterprise_id);

    ProductDto getProductById(Integer product_id);

    List<ProductDto> getAllProductsByEnterpriseId(Integer enterprise_id);

     void deleteProductById(Integer productId);

     ProductDto updateProduct(ProductDto productDto);

     ;

}
