package com.pro_servises.pro.controller;

import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.serviceImp.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add_product")
    public String addProduct(@RequestBody Product product, @RequestParam Integer provider_id) {
        productService.addProduct(product, provider_id);
        return "your product has been added";
    }

    @GetMapping("/get_product/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findProductById(id);
    }
    @GetMapping("/delete_product")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
