package com.pro_servises.pro.controller;

import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.serviceImp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;



    @PostMapping("/add_product")
    public String addProduct(@RequestBody Product product, @RequestParam Integer provider_id) {
        productServiceImp.addProduct(product, provider_id);
        return "your product has been added";
    }



    @GetMapping("/get_product/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productServiceImp.findProductById(id);
    }



    @GetMapping("/get_products_by_provider_id")
    public List<Product> getProductsByProviderId(@RequestParam Long provider_id) {
        return productServiceImp.findAllProductsByProviderId(provider_id);
    }



    @GetMapping("/delete_product/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productServiceImp.deleteProduct(id);
    }



}
