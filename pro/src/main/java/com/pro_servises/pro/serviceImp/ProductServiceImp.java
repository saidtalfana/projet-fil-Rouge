package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.exception.ConflictException;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.mapper.ProductMapper;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.model.Provider;
import com.pro_servises.pro.repository.ProductRepository;
import com.pro_servises.pro.repository.ProviderRepository;
import com.pro_servises.pro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;
   @Autowired
   private ProviderRepository providerRepository;


   @Autowired
    private ProductMapper productMapper;



//    ----------------------------ADD PRODUCT DTO --------------------------

    public ProductDto addProductDto(ProductDto productDto, Integer provider_id) {

        Product product = productMapper.mapToProduct(productDto);

        Provider provider = providerRepository.findById(provider_id).orElseThrow(
                () -> new NotFoundException("id "+ provider_id + " not found")
        );

        if (productRepository.findByName(productDto.getName()) != null) {
            throw new ConflictException("Another record with the same title exists");
        }

        product.setProvider(provider);

        Product savedProduct = productRepository.save(product);

        return productMapper.mapToProductDto(savedProduct);

    }

    //    ----------------------------GET PRODUCT DTO --------------------------

    public ProductDto getProductById(Long product_id) {
        Product product = productRepository.findById(product_id).orElseThrow(
                () -> new NotFoundException("id "+ product_id + " not found")
        );
        return productMapper.mapToProductDto(product);
    }

    //    ----------------------------GET PRODUCTS DTO --------------------------

public List<ProductDto> getAllProductsByProviderId(Integer provider_id){
        List<Product> products = productRepository.findAllProductsByProviderId(provider_id);
        return products.stream()
                .map(productMapper::mapToProductDto)
                .collect(Collectors.toList());
}
    //    ----------------------------DELETE PRODUCTS DTO --------------------------

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
    //    ----------------------------UPDATE PRODUCTS DTO --------------------------
    public ProductDto updateProduct(ProductDto productDto) {
        Product existingProduct = productRepository.findById(productDto.getProductId()).orElseThrow(
                () -> new NotFoundException("id "+ productDto.getProductId() + " not found")
        );

        existingProduct.setName(productDto.getName());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setCategory(productDto.getCategory());
        existingProduct.setProductStatus(productDto.getProductStatus());
        existingProduct.setImage(productDto.getImage());

        // Save the updated Product entity
        Product updatedProduct = productRepository.save(existingProduct);

        // Convert the updated Product entity to ProductDTO
        return productMapper.mapToProductDto(updatedProduct);

    }

//--------------------------------------------NORMAL---------------------------------------------

    @Override
    public Product addProduct(Product product, Integer provider_id) {
        Provider provider = providerRepository.findById(provider_id).orElseThrow(
                () -> new NotFoundException("id "+ provider_id + " not found")
        );

        if (productRepository.findByName(product.getName()) != null) {
            throw new ConflictException("Another record with the same title exists");
        }

        product.setProvider(provider);
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long productId) {
        try{
        Product product = productRepository.findById(productId).get();
        return product;
        }catch (NoSuchElementException ex) {
            throw new NotFoundException(String.format("No Record with the id [%s] was found in our database", productId));
        }
    }

    @Override
    public List<Product> findAllProductsByProviderId(Integer provider_id) {
        return productRepository.findAllProductsByProviderId(provider_id);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

}
