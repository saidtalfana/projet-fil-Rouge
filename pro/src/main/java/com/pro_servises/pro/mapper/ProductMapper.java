package com.pro_servises.pro.mapper;

import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    ProductDto mapToProductDto(Product product);

    Product mapToProduct(ProductDto productDto);

}
