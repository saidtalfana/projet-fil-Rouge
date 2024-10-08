package com.pro_servises.pro.mapper;


import com.pro_servises.pro.dto.EnterpriseDto;
import com.pro_servises.pro.model.Enterprise;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface EnterpriseMapper {


   EnterpriseDto mapToEnterpriseDto(Enterprise enterprise);
   Enterprise mapToEnterprise(EnterpriseDto enterpriseDto);

}
