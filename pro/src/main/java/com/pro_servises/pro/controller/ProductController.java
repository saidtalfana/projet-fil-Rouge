package com.pro_servises.pro.controller;

import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.serviceImp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;



    @PostMapping("/add_product")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto, @RequestParam Integer enterprise_id) {
      ProductDto createdProduct=  productServiceImp.addProductDto(productDto, enterprise_id);
        return ResponseEntity.ok(createdProduct);
    }



    @GetMapping("/get_product/{id}")
    public ProductDto getProduct(@PathVariable Integer id) {
        return productServiceImp.getProductById(id);
    }



    @GetMapping("/get_products_by_provider_id")
    public List<ProductDto> getProductsByProviderId(@RequestParam Integer enterprise_id) {
        return productServiceImp.getAllProductsByEnterpriseId(enterprise_id);
    }



    @DeleteMapping("/delete_product/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productServiceImp.deleteProductById(id);
    }


    @PutMapping("/update_product")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productServiceImp.updateProduct(productDto);
    }

}
