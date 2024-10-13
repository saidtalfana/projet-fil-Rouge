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

/**
 * Implementation of the ProductService interface for managing product-related operations.
 */
@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final EntepriseRepository entepriseRepository;
    private final ProductMapper productMapper;

    /**
     * Constructor for ProductServiceImp.
     *
     * @param productRepository The repository for accessing product data.
     * @param entepriseRepository The repository for accessing enterprise data.
     * @param productMapper The mapper for converting between Product and ProductDto.
     */
    public ProductServiceImp(ProductRepository productRepository, EntepriseRepository entepriseRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.entepriseRepository = entepriseRepository;
        this.productMapper = productMapper;
    }

    /**
     * Adds a new product based on the provided ProductDto and enterprise ID.
     *
     * @param productDto The DTO containing the product details.
     * @param enterpriseId The ID of the enterprise to which the product belongs.
     * @param imageBytes The image data for the product.
     * @return The created ProductDto.
     * @throws NotFoundException If the enterprise is not found.
     * @throws ConflictException If another product with the same name exists.
     */
    @Override
    public ProductDto addProductDto(ProductDto productDto, Integer enterpriseId, byte[] imageBytes) {
        Product product = productMapper.mapToProduct(productDto);
        Enterprise enterprise = entepriseRepository.findById(enterpriseId).orElseThrow(
                () -> new NotFoundException("id " + enterpriseId + " not found"));
        if (productRepository.findByName(productDto.getName()) != null) {
            throw new ConflictException("Another record with the same title exists");
        }

        product.setEnterprise(enterprise);
        product.setImage(imageBytes);
        Product savedProduct = productRepository.save(product);
        return productMapper.mapToProductDto(savedProduct);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The ProductDto associated with the given ID.
     * @throws NotFoundException If the product is not found.
     */
    @Override
    public ProductDto getProductById(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("id " + productId + " not found"));
        return productMapper.mapToProductDto(product);
    }

    /**
     * Retrieves all products associated with a given enterprise ID.
     *
     * @param enterpriseId The ID of the enterprise whose products are to be retrieved.
     * @return A list of ProductDto for the products associated with the enterprise.
     */
    @Override
    public List<ProductDto> getAllProductsByEnterpriseId(Integer enterpriseId) {
        List<Product> products = productRepository.findAllProductsByEnterpriseId(enterpriseId);
        return products.stream()
                .map(productMapper::mapToProductDto)
                .collect(Collectors.toList());
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId The ID of the product to delete.
     */
    @Override
    public void deleteProductById(Integer productId) {
        productRepository.deleteById(productId);
    }

    /**
     * Updates an existing product based on the provided ProductDto.
     *
     * @param productDto The DTO containing the updated product details.
     * @return The updated ProductDto.
     * @throws NotFoundException If the product is not found.
     */
    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product existingProduct = productRepository.findById(productDto.getProductId()).orElseThrow(
                () -> new NotFoundException("id " + productDto.getProductId() + " not found"));
        existingProduct.setName(productDto.getName());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setCategory(productDto.getCategory());
        existingProduct.setProductStatus(productDto.getProductStatus());
        existingProduct.setImage(productDto.getImage());
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.mapToProductDto(updatedProduct);
    }

    /**
     * Retrieves all products.
     *
     * @return A list of all ProductDto.
     */
    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::mapToProductDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a paginated list of products.
     *
     * @param pageable The pagination information.
     * @return A page of Product objects.
     */
    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * Filters products based on category, price range, and name.
     *
     * @param category The category to filter by.
     * @param minPrice The minimum price of the products.
     * @param maxPrice The maximum price of the products.
     * @param name The name to filter by.
     * @return A list of filtered Product objects.
     */
    @Override
    public List<Product> filterProducts(Category category, Double minPrice, Double maxPrice, String name) {
        Specification<Product> spec = ProductSpecification.filterByCategoryAndPriceAndName(category, minPrice, maxPrice, name);
        return productRepository.findAll(spec);
    }

    /**
     * Recommends products based on category and price.
     *
     * @param category The category to recommend from.
     * @param price The price to filter recommendations.
     * @return A list of recommended Product objects.
     */
    @Override
    public List<Product> recommendByCategoryAndPrice(Category category, Double price) {
        return productRepository.findByCategoryAndPrice(category, price);
    }

    /**
     * Retrieves the enterprise associated with a given product ID.
     *
     * @param productId The ID of the product whose enterprise is to be retrieved.
     * @return The Enterprise associated with the product ID.
     */
    @Override
    public Enterprise getEnterpriseByProductId(Integer productId) {
        return entepriseRepository.findByProductId(productId);
    }

    /**
     * Counts the number of products associated with a given enterprise ID.
     *
     * @param enterpriseId The ID of the enterprise to count products for.
     * @return The count of products for the specified enterprise.
     */
    @Override
    public long countProductsByEnterprise(Long enterpriseId) {
        return productRepository.countProductsByEnterprise(enterpriseId);
    }

    /**
     * Retrieves all products that have been ordered.
     *
     * @return A list of Product objects with orders.
     */
    @Override
    public List<Product> getProductsWithOrders() {
        return productRepository.findProductsWithOrders();
    }

    /**
     * Counts the number of products by their status for a given enterprise ID.
     *
     * @param enterpriseId The ID of the enterprise to count products by status for.
     * @return A map containing product statuses and their respective counts.
     */
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

    /**
     * Retrieves the average star rating for a given product ID.
     *
     * @param productId The ID of the product to get the average stars for.
     * @return The average star rating for the specified product.
     */
    @Override
    public Double getAverageStarsForProduct(Integer productId) {
        return productRepository.getAverageStarsForProduct(productId);
    }
}
