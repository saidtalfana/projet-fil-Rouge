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

    /**
     * Add a new product
     *
     * @param name        the name of the product
     * @param description the description of the product
     * @param price       the price of the product
     * @param category    the category of the product
     * @param productStatus the status of the product (e.g., available, out of stock)
     * @param enterpriseId the ID of the enterprise associated with the product
     * @param imageFile   the image file for the product
     * @return the created ProductDto object
     * @throws IOException if there is an error processing the image file
     */
    @PostMapping("/add_product")
    public ResponseEntity<ProductDto> addProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam Category category,
            @RequestParam ProductStatus productStatus,
            @RequestParam Integer enterpriseId,
            @RequestPart("image") MultipartFile imageFile) throws IOException {

        byte[] imageBytes = imageFile.getBytes();
        ProductDto productDto = ProductDto.builder()
                .name(name)
                .description(description)
                .price(price)
                .category(category)
                .productStatus(productStatus)
                .build();

        ProductDto createdProduct = productServiceImp.addProductDto(productDto, enterpriseId, imageBytes);
        return ResponseEntity.ok(createdProduct);
    }

    /**
     * Get a product by its ID
     *
     * @param id the ID of the product
     * @return the ProductDto object associated with the specified ID
     */
    @GetMapping("/get_product/{id}")
    public ProductDto getProduct(@PathVariable Integer id) {
        return productServiceImp.getProductById(id);
    }

    /**
     * Get all products by the enterprise ID
     *
     * @param enterpriseId the ID of the enterprise
     * @return a list of ProductDto objects associated with the specified enterprise ID
     */
    @GetMapping("/get_products_by_enterprise_id")
    public List<ProductDto> getProductsByProviderId(@RequestParam Integer enterpriseId) {
        return productServiceImp.getAllProductsByEnterpriseId(enterpriseId);
    }

    /**
     * Delete a product by its ID
     *
     * @param id the ID of the product to be deleted
     */
    @DeleteMapping("/delete_product/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productServiceImp.deleteProductById(id);
    }

    /**
     * Update a product
     *
     * @param productDto the ProductDto object containing updated product details
     * @return the updated ProductDto object
     */
    @PutMapping("/update_product")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productServiceImp.updateProduct(productDto);
    }

    /**
     * Get all products
     *
     * @return a list of all ProductDto objects
     */
    @GetMapping("/get_all_product")
    public List<ProductDto> getAllProduct() {
        return productServiceImp.getAllProduct();
    }

    /**
     * Get a paginated list of products
     *
     * @param page the page number to retrieve (default is 0)
     * @param size the number of products per page (default is 12)
     * @return a Page of Product objects
     */
    @GetMapping("/pagination")
    public Page<Product> getProducts(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "12") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productServiceImp.getProducts(pageable);
    }

    /**
     * Filter products based on various criteria
     *
     * @param category   optional category to filter products
     * @param minPrice   optional minimum price to filter products
     * @param maxPrice   optional maximum price to filter products
     * @param name       optional name to filter products
     * @return a list of filtered Product objects
     */
    @GetMapping("/filter")
    public List<Product> filterProducts(
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String name) {
        return productServiceImp.filterProducts(category, minPrice, maxPrice, name);
    }

    /**
     * Recommend products based on category and price
     *
     * @param category the category to filter recommended products
     * @param price    the maximum price of recommended products
     * @return a ResponseEntity containing a list of recommended Product objects
     */
    @GetMapping("/recommend")
    public ResponseEntity<List<Product>> recommendProducts(
            @RequestParam Category category,
            @RequestParam Double price) {
        List<Product> recommendedProducts = productServiceImp.recommendByCategoryAndPrice(category, price);
        return ResponseEntity.ok(recommendedProducts);
    }

    /**
     * Count the number of products by enterprise ID
     *
     * @param enterpriseId the ID of the enterprise
     * @return the count of products associated with the specified enterprise ID
     */
    @GetMapping("/countByEnterprise/{enterpriseId}")
    public long countProductsByEnterprise(@PathVariable Long enterpriseId) {
        return productServiceImp.countProductsByEnterprise(enterpriseId);
    }

    /**
     * Get all products that have associated orders
     *
     * @return a list of Product objects with orders
     */
    @GetMapping("/with-orders")
    public List<Product> getProductsWithOrders() {
        return productServiceImp.getProductsWithOrders();
    }

    /**
     * Get the count of products by their status for a specific enterprise
     *
     * @param enterpriseId the ID of the enterprise
     * @return a map containing product statuses and their respective counts
     */
    @GetMapping("/count/status/{enterpriseId}")
    public Map<String, Long> getProductsCountByStatus(@PathVariable Integer enterpriseId) {
        return productServiceImp.countProductsByStatus(enterpriseId);
    }

    /**
     * Get the average star rating for a specific product
     *
     * @param productId the ID of the product
     * @return the average star rating for the specified product
     */
    @GetMapping("/average-stars/{productId}")
    public Double getAverageStars(@PathVariable Integer productId) {
        return productServiceImp.getAverageStarsForProduct(productId);
    }
}
