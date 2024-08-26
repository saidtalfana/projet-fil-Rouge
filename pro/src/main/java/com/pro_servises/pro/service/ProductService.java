package com.pro_servises.pro.service;

import com.pro_servises.pro.model.Product;

import java.util.List;

public interface ProductService {

    public Product addProduct(Product product, Integer provider_id);

    Product findProductById(Long productId);

    public List<Product> findAllProductsByProviderId(Long provider_id);

    public void deleteProduct(Long productId);
}
