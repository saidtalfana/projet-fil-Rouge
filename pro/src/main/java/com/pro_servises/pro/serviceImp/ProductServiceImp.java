package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.exception.ConflictException;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.model.Provider;
import com.pro_servises.pro.repository.ProductRepository;
import com.pro_servises.pro.repository.ProviderRepository;
import com.pro_servises.pro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;
   @Autowired
   private ProviderRepository providerRepository;


//   @Autowired
//    private ModelMapper modelMapper;


    @Override
    public Product addProduct(Product product, Integer provider_id) {
        Provider provider = providerRepository.findById(provider_id).orElseThrow(
                () -> new NotFoundException("id "+ provider_id + " not found")
        );


        if (productRepository.findByName(product.getName()) != null) {
            throw new ConflictException("Another record with the same title exists");
        }


        product.setProvider(provider);
        return productRepository.save(product);    }


    public Product addProduct(Product product) {
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
    public List<Product> findAllProductsByProviderId(Long provider_id) {
        return productRepository.findAllProductsByProviderId(provider_id);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

}
