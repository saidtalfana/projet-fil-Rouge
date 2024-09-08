package com.pro_servises.pro.mapper;


import com.pro_servises.pro.dto.EnterpriseDto;
import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.model.Enterprise;
import com.pro_servises.pro.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnterpriseMapper {

    EnterpriseMapper INSTANCE = Mappers.getMapper( EnterpriseMapper.class );

   EnterpriseDto mapToEnterpriseDto(Enterprise enterprise);
   Enterprise mapToEnterprise(EnterpriseDto enterpriseDto);

}
