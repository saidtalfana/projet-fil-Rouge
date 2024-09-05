package com.pro_servises.pro.service;

import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.model.Product;

import java.util.List;

public interface ProductService {

    ProductDto addProductDto(ProductDto productDto, Integer provider_id);

    ProductDto getProductById(Integer product_id);

    List<ProductDto> getAllProductsByProviderId(Integer provider_id);

     void deleteProductById(Integer productId);

     ProductDto updateProduct(ProductDto productDto);

}
