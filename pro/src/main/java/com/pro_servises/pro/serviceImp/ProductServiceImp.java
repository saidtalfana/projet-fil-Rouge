package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.OrderDto;
import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.exception.ConflictException;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.mapper.ProductMapper;
import com.pro_servises.pro.model.Enterprise;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.model.Provider;
import com.pro_servises.pro.repository.EntepriseRepository;
import com.pro_servises.pro.repository.ProductRepository;
import com.pro_servises.pro.repository.ProviderRepository;
import com.pro_servises.pro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService{

    @Autowired
          private ProductRepository productRepository;

    @Autowired
    private EntepriseRepository entepriseRepository;

    @Autowired
           private ProductMapper productMapper;




    @Override
    public ProductDto addProductDto(ProductDto productDto, Integer enterprise_id , byte[] imageBytes) {
        Product product = productMapper.mapToProduct(productDto);
        Enterprise enterprise = entepriseRepository.findById(enterprise_id).orElseThrow(
                () -> new NotFoundException("id "+ enterprise_id + " not found"));
                  if (productRepository.findByName(productDto.getName()) != null) {
                         throw new ConflictException("Another record with the same title exists");}

        product.setEnterprise(enterprise);
                  product.setImage(imageBytes);
        Product savedProduct = productRepository.save(product);
        return productMapper.mapToProductDto(savedProduct);
    }


    @Override
    public ProductDto getProductById(Integer product_id) {
        Product product = productRepository.findById(product_id).orElseThrow(
                () -> new NotFoundException("id "+ product_id + " not found"));
        return productMapper.mapToProductDto(product);
    }


    @Override
    public List<ProductDto> getAllProductsByEnterpriseId(Integer enterprise_id){
        List<Product> products = productRepository.findAllProductsByEnterpriseId(enterprise_id);
        return products.stream()
                .map(productMapper::mapToProductDto)
                .collect(Collectors.toList());
}

    @Override
    public void deleteProductById(Integer productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product existingProduct = productRepository.findById(productDto.getProductId()).orElseThrow(
                () -> new NotFoundException("id "+ productDto.getProductId() + " not found"));
        existingProduct.setName(productDto.getName());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setCategory(productDto.getCategory());
        existingProduct.setProductStatus(productDto.getProductStatus());
        existingProduct.setImage(productDto.getImage());
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.mapToProductDto(updatedProduct);

    }

     @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::mapToProductDto)
                .collect(Collectors.toList());
    }



//    @Override
//    public List<Product> searchProducts(Float price, String name, String category) {
//        if (price != null && name != null && category != null) {
////            return eventRepository.findByDateAndLocationAndCategory(date, location, category);
//            return productRepository.findByNameAndPriceAndCategory(name, price, category);
//        } else if (price != null && name != null) {
//            return productRepository.findByNameAndPrice(name, price);
//        } else if (price != null && category != null) {
//           return productRepository.findByPriceAndCategory( price, category);
//        } else if (name != null && category != null) {
//            return productRepository.findByNameAndCategory(name, category);
//        } else if (price != null) {
//            return productRepository.findByProductPrice(price);
//        } else if (name != null) {
//            return productRepository.findByProductName(name);
//        } else if (category != null) {
//            return productRepository.findByCategory(category);
//        } else {
//            return productRepository.findAll();
//        }
//    }
}
