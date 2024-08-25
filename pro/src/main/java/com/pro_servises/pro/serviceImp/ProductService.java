//package com.pro_servises.pro.serviceImp;
//
//import com.pro_servises.pro.exception.ResourceNotFoundException;
//import com.pro_servises.pro.model.Product;
//import com.pro_servises.pro.model.Provider;
//import com.pro_servises.pro.repository.ProductRepository;
//import com.pro_servises.pro.repository.ProviderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ProductService {
//
//    @Autowired
//    private ProductRepository productRepository;
//   @Autowired
//   private ProviderRepository providerRepository;
//
//    public Product addProduct(Product product,Long provider_id) {
//        Provider provider = providerRepository.findById(provider_id).orElseThrow(
//                () -> new ResourceNotFoundException("id "+ provider_id + " not found")
//        );
//        product.setProvider(provider);
//        return productRepository.save(product);
//    }
//}
