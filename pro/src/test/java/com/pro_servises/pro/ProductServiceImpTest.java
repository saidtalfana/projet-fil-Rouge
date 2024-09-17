package com.pro_servises.pro;

import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.exception.ConflictException;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.mapper.ProductMapper;
import com.pro_servises.pro.model.Enterprise;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.repository.EntepriseRepository;
import com.pro_servises.pro.repository.ProductRepository;
import com.pro_servises.pro.serviceImp.ProductServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImpTest {

    @InjectMocks
    private ProductServiceImp productServiceImp;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private EntepriseRepository entepriseRepository;

    @Mock
    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProductDto() {
        // Given
        ProductDto productDto = new ProductDto();
        productDto.setName("Product Name");

        Enterprise enterprise = new Enterprise();
        Product product = new Product();
        Product savedProduct = new Product();
        savedProduct.setProductId(1);

        when(entepriseRepository.findById(anyInt())).thenReturn(Optional.of(enterprise));
        when(productRepository.findByName(productDto.getName())).thenReturn(null);
        when(productMapper.mapToProduct(productDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(savedProduct);
        when(productMapper.mapToProductDto(savedProduct)).thenReturn(productDto);

        // When
        ProductDto result = productServiceImp.addProductDto(productDto, 1);

        // Then
        assertNotNull(result);
        assertEquals(productDto.getName(), result.getName());
        verify(entepriseRepository, times(1)).findById(1);
        verify(productRepository, times(1)).findByName(productDto.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testAddProductDto_Conflict() {
        // Given
        ProductDto productDto = new ProductDto();
        productDto.setName("Product Name");

        when(productRepository.findByName(productDto.getName())).thenReturn(new Product());

        // When/Then
        ConflictException thrown = assertThrows(
                ConflictException.class,
                () -> productServiceImp.addProductDto(productDto, 1)
        );
        assertEquals("Another record with the same title exists", thrown.getMessage());
    }

    @Test
    void testGetProductById() {
        // Given
        ProductDto productDto = new ProductDto();
        Product product = new Product();
        product.setProductId(1);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(productMapper.mapToProductDto(product)).thenReturn(productDto);

        // When
        ProductDto result = productServiceImp.getProductById(1);

        // Then
        assertNotNull(result);
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    void testGetProductById_NotFound() {
        // Given
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

        // When/Then
        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> productServiceImp.getProductById(1)
        );
        assertEquals("id 1 not found", thrown.getMessage());
    }

    @Test
    void testGetAllProductsByEnterpriseId() {
        // Given
        ProductDto productDto = new ProductDto();
        List<Product> products = Collections.singletonList(new Product());
        List<ProductDto> productDtos = Collections.singletonList(productDto);

        when(productRepository.findAllProductsByEnterpriseId(1)).thenReturn(products);
        when(productMapper.mapToProductDto(any(Product.class))).thenReturn(productDto);

        // When
        List<ProductDto> result = productServiceImp.getAllProductsByEnterpriseId(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(productRepository, times(1)).findAllProductsByEnterpriseId(1);
    }

    @Test
    void testDeleteProductById() {
        // When
        productServiceImp.deleteProductById(1);

        // Then
        verify(productRepository, times(1)).deleteById(1);
    }

    @Test
    void testUpdateProduct() {
        // Given
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1);
        Product existingProduct = new Product();
        Product updatedProduct = new Product();

        when(productRepository.findById(1)).thenReturn(Optional.of(existingProduct));
        when(productMapper.mapToProduct(productDto)).thenReturn(existingProduct);
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);
        when(productMapper.mapToProductDto(updatedProduct)).thenReturn(productDto);

        // When
        ProductDto result = productServiceImp.updateProduct(productDto);

        // Then
        assertNotNull(result);
        assertEquals(productDto.getProductId(), result.getProductId());
        verify(productRepository, times(1)).findById(1);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    void testUpdateProduct_NotFound() {
        // Given
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1);

        when(productRepository.findById(1)).thenReturn(Optional.empty());

        // When/Then
        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> productServiceImp.updateProduct(productDto)
        );
        assertEquals("id 1 not found", thrown.getMessage());
    }

    @Test
    void testGetAllProduct() {
        // Given
        ProductDto productDto = new ProductDto();
        List<Product> products = Collections.singletonList(new Product());
        List<ProductDto> productDtos = Collections.singletonList(productDto);

        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.mapToProductDto(any(Product.class))).thenReturn(productDto);

        // When
        List<ProductDto> result = productServiceImp.getAllProduct();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(productRepository, times(1)).findAll();
    }
}
