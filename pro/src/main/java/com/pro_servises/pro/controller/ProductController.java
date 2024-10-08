package com.pro_servises.pro.controller;

import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.enums.Category;
import com.pro_servises.pro.enums.ProductStatus;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.serviceImp.ProductServiceImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductServiceImp productServiceImp;

    public ProductController(ProductServiceImp productServiceImp) {
        this.productServiceImp = productServiceImp;
    }


    @PostMapping("/add_product")
    public ResponseEntity<ProductDto> addProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam Category category,
            @RequestParam ProductStatus  productStatus,
            @RequestParam Integer enterpriseId,
            @RequestPart("image") MultipartFile imageFile) throws IOException {



        // Convert the image file to bytes
        byte[] imageBytes = imageFile.getBytes();

        // Create the ProductDto with the provided parameters
        ProductDto productDto = ProductDto.builder()
                .name(name)
                .description(description)
                .price(price)
                .category(category)
                .productStatus(productStatus) // Set as needed
                .build();

        // Call the service to save the product
        ProductDto createdProduct = productServiceImp.addProductDto(productDto, enterpriseId, imageBytes);

        // Return the created product DTO
        return ResponseEntity.ok(createdProduct);
    }







    @GetMapping("/get_product/{id}")
    public ProductDto getProduct(@PathVariable Integer id) {
        return productServiceImp.getProductById(id);
    }



    @GetMapping("/get_products_by_enterprise_id")
    public List<ProductDto> getProductsByProviderId(@RequestParam Integer enterpriseId) {
        return productServiceImp.getAllProductsByEnterpriseId(enterpriseId);
    }



    @DeleteMapping("/delete_product/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productServiceImp.deleteProductById(id);
    }


    @PutMapping("/update_product")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productServiceImp.updateProduct(productDto);
    }

    @GetMapping("/get_all_product")
    public List<ProductDto> getAllProduct() {
        return productServiceImp.getAllProduct();
    }

    @GetMapping("/pagination")
    public Page<Product> getProducts(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "12") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productServiceImp.getProducts(pageable);
    }

    @GetMapping("/filter")
    public List<Product> filterProducts(
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String name) {
        return productServiceImp.filterProducts(category, minPrice, maxPrice, name);
    }

    @GetMapping("/recommend")
    public ResponseEntity<List<Product>> recommendProducts(
            @RequestParam Category category,
            @RequestParam Double price) {
        List<Product> recommendedProducts = productServiceImp.recommendByCategoryAndPrice(category, price);
        return ResponseEntity.ok(recommendedProducts);
    }
    @GetMapping("/countByEnterprise/{enterpriseId}")
    public long countProductsByEnterprise(@PathVariable Long enterpriseId) {
        return productServiceImp.countProductsByEnterprise(enterpriseId);
    }

    @GetMapping("/with-orders")
    public List<Product> getProductsWithOrders() {
        return productServiceImp.getProductsWithOrders();
    }

    @GetMapping("/count/status/{enterpriseId}")
    public Map<String, Long> getProductsCountByStatus(@PathVariable Integer enterpriseId) {
        return productServiceImp.countProductsByStatus(enterpriseId);
    }

    @GetMapping("/average-stars/{productId}")
    public Double getAverageStars(@PathVariable Integer productId) {
        return productServiceImp.getAverageStarsForProduct(productId);
    }

}
