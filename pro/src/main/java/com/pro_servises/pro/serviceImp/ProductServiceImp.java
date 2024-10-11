package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.Specification.ProductSpecification;
import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.enums.Category;
import com.pro_servises.pro.enums.ProductStatus;
import com.pro_servises.pro.exception.ConflictException;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.mapper.ProductMapper;
import com.pro_servises.pro.model.Enterprise;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.repository.EntepriseRepository;
import com.pro_servises.pro.repository.ProductRepository;
import com.pro_servises.pro.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;

    private final EntepriseRepository entepriseRepository;

    private final ProductMapper productMapper;

    public ProductServiceImp(ProductRepository productRepository, EntepriseRepository entepriseRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.entepriseRepository = entepriseRepository;
        this.productMapper = productMapper;
    }


    @Override
    public ProductDto addProductDto(ProductDto productDto, Integer enterpriseId , byte[] imageBytes) {
        Product product = productMapper.mapToProduct(productDto);
        Enterprise enterprise = entepriseRepository.findById(enterpriseId).orElseThrow(
                () -> new NotFoundException("id "+ enterpriseId + " not found"));
                  if (productRepository.findByName(productDto.getName()) != null) {
                         throw new ConflictException("Another record with the same title exists");}

        product.setEnterprise(enterprise);
                  product.setImage(imageBytes);
        Product savedProduct = productRepository.save(product);
        return productMapper.mapToProductDto(savedProduct);
    }


    @Override
    public ProductDto getProductById(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("id "+ productId + " not found"));
        return productMapper.mapToProductDto(product);
    }


    @Override
    public List<ProductDto> getAllProductsByEnterpriseId(Integer enterpriseId){
        List<Product> products = productRepository.findAllProductsByEnterpriseId(enterpriseId);
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

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    @Override
    public List<Product> filterProducts(Category category, Double minPrice, Double maxPrice, String name) {
        Specification<Product> spec = ProductSpecification.filterByCategoryAndPriceAndName(category, minPrice, maxPrice, name);
        return productRepository.findAll(spec);
    }

    @Override
    public List<Product> recommendByCategoryAndPrice(Category category, Double price) {
        return productRepository.findByCategoryAndPrice(category, price);
    }
    @Override
    public Enterprise getEnterpriseByProductId(Integer productId) {
        return entepriseRepository.findByProductId(productId);
    }

    @Override
    public long countProductsByEnterprise(Long enterpriseId) {
        return productRepository.countProductsByEnterprise(enterpriseId);
    }
    @Override
    public List<Product> getProductsWithOrders() {

        return productRepository.findProductsWithOrders();
    }
    @Override
    public Map<String, Long> countProductsByStatus(Integer enterpriseId) {
        List<Object[]> results = productRepository.countProductsByStatus(enterpriseId);
        Map<String, Long> statusCountMap = new HashMap<>();

        for (Object[] result : results) {
            // Assume the first element is the status and the second is the count
            String status = ((ProductStatus) result[0]).name(); // Convert enum to String
            Long count = ((Number) result[1]).longValue();
            statusCountMap.put(status, count);
        }

        return statusCountMap;
    }
   @Override
    public Double getAverageStarsForProduct(Integer productId) {
        return productRepository.getAverageStarsForProduct(productId);
    }
}
