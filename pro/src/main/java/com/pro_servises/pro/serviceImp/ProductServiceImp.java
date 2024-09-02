package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.exception.ConflictException;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.mapper.ProductMapper;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.model.Provider;
import com.pro_servises.pro.repository.ProductRepository;
import com.pro_servises.pro.repository.ProviderRepository;
import com.pro_servises.pro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService{

    @Autowired
          private ProductRepository productRepository;

    @Autowired
          private ProviderRepository providerRepository;

    @Autowired
           private ProductMapper productMapper;




    @Override
    public ProductDto addProductDto(ProductDto productDto, Integer provider_id) {
        Product product = productMapper.mapToProduct(productDto);
        Provider provider = providerRepository.findById(provider_id).orElseThrow(
                () -> new NotFoundException("id "+ provider_id + " not found"));
                  if (productRepository.findByName(productDto.getName()) != null) {
                         throw new ConflictException("Another record with the same title exists");}

        product.setProvider(provider);
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
    public List<ProductDto> getAllProductsByProviderId(Integer provider_id){
        List<Product> products = productRepository.findAllProductsByProviderId(provider_id);
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



}
