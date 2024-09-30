package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.Specification.ProductSpecification;
import com.pro_servises.pro.dto.OrderDto;
import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.enums.Category;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> filterProducts(Category category, Double minPrice, Double maxPrice, String name) {
        Specification<Product> spec = ProductSpecification.filterByCategoryAndPriceAndName(category, minPrice, maxPrice, name);
        return productRepository.findAll(spec);
    }


    public List<Product> recommendByCategoryAndPrice(Category category, Double price) {
        return productRepository.findByCategoryAndPrice(category, price);
    }

    public Enterprise getEnterpriseByProductId(Integer productId) {
        return entepriseRepository.findByProductId(productId);
    }
}
