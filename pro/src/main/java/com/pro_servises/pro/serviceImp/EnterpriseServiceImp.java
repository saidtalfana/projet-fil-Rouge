package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.EnterpriseDto;
import com.pro_servises.pro.mapper.EnterpriseMapper;
import com.pro_servises.pro.model.Enterprise;
import com.pro_servises.pro.model.Provider;
import com.pro_servises.pro.repository.EntepriseRepository;
import com.pro_servises.pro.repository.ProviderRepository;
import com.pro_servises.pro.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EnterpriseServiceImp implements EnterpriseService {

    @Autowired
    private EntepriseRepository enterpriseRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Override
    public EnterpriseDto addEnterprise(EnterpriseDto enterpriseDto, Integer provider_id) {

        Enterprise enterprise = enterpriseMapper.mapToEnterprise(enterpriseDto);
       Provider provider = providerRepository.findById(provider_id).get();
       enterprise.setProvider(provider);
       Enterprise savedEnterprise = enterpriseRepository.save(enterprise);
       return enterpriseMapper.mapToEnterpriseDto(savedEnterprise);

    }

    @Override
    public EnterpriseDto getEnterpriseById(Integer provider_id) {
 Enterprise enterprise = enterpriseRepository.findById(provider_id).get();
    return enterpriseMapper.mapToEnterpriseDto(enterprise);
    }



    @Override
    public EnterpriseDto updateEnterprise(EnterpriseDto enterpriseDto) {
 Enterprise existingEnterprise = enterpriseRepository.findById(enterpriseDto.getEnterpriseId()).get();
 existingEnterprise.setEnterpriseName(enterpriseDto.getEnterpriseName());
 existingEnterprise.setEnterpriseLogo(enterpriseDto.getEnterpriseLogo());
 existingEnterprise.setEnterpriseDescription(enterpriseDto.getEnterpriseDescription());
 existingEnterprise.setActivity(enterpriseDto.getActivity());
 Enterprise updatedEnterprise = enterpriseRepository.save(existingEnterprise);

 return enterpriseMapper.mapToEnterpriseDto(updatedEnterprise);
    }
}


//@Override
//public ProductDto addProductDto(ProductDto productDto, Integer provider_id) {
//    Product product = productMapper.mapToProduct(productDto);
//    Provider provider = providerRepository.findById(provider_id).orElseThrow(
//            () -> new NotFoundException("id "+ provider_id + " not found"));
//    if (productRepository.findByName(productDto.getName()) != null) {
//        throw new ConflictException("Another record with the same title exists");}
//
//    product.setProvider(provider);
//    Product savedProduct = productRepository.save(product);
//    return productMapper.mapToProductDto(savedProduct);}
//@Override
//public ProductDto getProductById(Integer product_id) {
//    Product product = productRepository.findById(product_id).orElseThrow(
//            () -> new NotFoundException("id "+ product_id + " not found"));
//    return productMapper.mapToProductDto(product);
//}
//
//
//@Override
//public List<ProductDto> getAllProductsByProviderId(Integer provider_id){
//    List<Product> products = productRepository.findAllProductsByProviderId(provider_id);
//    return products.stream()
//            .map(productMapper::mapToProductDto)
//            .collect(Collectors.toList());
//}
//
//@Override
//public void deleteProductById(Integer productId) {
//    productRepository.deleteById(productId);
//}
//
//@Override
//public ProductDto updateProduct(ProductDto productDto) {
//    Product existingProduct = productRepository.findById(productDto.getProductId()).orElseThrow(
//            () -> new NotFoundException("id "+ productDto.getProductId() + " not found"));
//    existingProduct.setName(productDto.getName());
//    existingProduct.setDescription(productDto.getDescription());
//    existingProduct.setPrice(productDto.getPrice());
//    existingProduct.setCategory(productDto.getCategory());
//    existingProduct.setProductStatus(productDto.getProductStatus());
//    existingProduct.setImage(productDto.getImage());
//    Product updatedProduct = productRepository.save(existingProduct);
//    return productMapper.mapToProductDto(updatedProduct);
//
//}




//}
