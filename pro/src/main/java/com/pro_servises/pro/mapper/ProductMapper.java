package com.pro_servises.pro.mapper;

import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

//    @Mapping(source = "email", target = "emailAddress")
    ProductDto mapToProductDto(Product product);

//    @Mapping(source = "emailAddress", target = "email")
    Product mapToProduct(ProductDto productDto);

}
