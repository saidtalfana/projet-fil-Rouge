package com.pro_servises.pro.controller;

import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.enums.Category;
import com.pro_servises.pro.enums.ProductStatus;
import com.pro_servises.pro.mapper.ProductMapper;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.serviceImp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images";


//    @PostMapping("/add_product")
//    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto,
//                                                 @RequestParam Integer enterprise_id,
//                                                 @RequestPart("file")MultipartFile file
//                                          ) throws IOException {
//
//
//
//
//      ProductDto createdProduct=  productServiceImp.addProductDto(productDto, enterprise_id);
//        return ResponseEntity.ok(createdProduct);
//    }

//    @PostMapping("/add_product")
//    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto,
//                                                 @RequestParam Integer enterprise_id,
//                                                 @RequestParam("image") MultipartFile imageFile) throws IOException {
//        // Convert the file to bytes and set it in the ProductDto if needed
//        byte[] imageBytes = imageFile.getBytes();
//        // Save the product using the service
//
//        ProductDto createdProduct = productServiceImp.addProductDto(productDto, enterprise_id,imageBytes);
//
//        return ResponseEntity.ok(createdProduct);
//    }

    @PostMapping("/add_product")
    public ResponseEntity<ProductDto> addProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam Category category,
            @RequestParam ProductStatus  productStatus,
            @RequestParam Integer enterprise_id,
            @RequestPart("image") MultipartFile imageFile) throws IOException {

        // Log the file size
        System.out.println("Uploaded file size: " + imageFile.getSize());

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
        ProductDto createdProduct = productServiceImp.addProductDto(productDto, enterprise_id, imageBytes);

        // Return the created product DTO
        return ResponseEntity.ok(createdProduct);
    }







    @GetMapping("/get_product/{id}")
    public ProductDto getProduct(@PathVariable Integer id) {
        return productServiceImp.getProductById(id);
    }



    @GetMapping("/get_products_by_enterprise_id")
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

    @GetMapping("/get_all_product")
    public List<ProductDto> getAllProduct() {
        return productServiceImp.getAllProduct();
    }

//    @GetMapping("/search")
//    List<Product> getProductBySearch( @RequestParam(required = false) Float price,
//                                         @RequestParam(required = false) String name,
//                                         @RequestParam(required = false) String category) {
//        return productServiceImp.searchProducts( price,name,category);
//    }


}
