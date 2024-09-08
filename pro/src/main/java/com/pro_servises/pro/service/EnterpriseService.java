package com.pro_servises.pro.service;

import com.pro_servises.pro.dto.EnterpriseDto;

public interface EnterpriseService {


    EnterpriseDto addEnterprise(EnterpriseDto enterpriseDto, Integer provider_id);

    EnterpriseDto getEnterpriseById(Integer provider_id);

    EnterpriseDto updateEnterprise(EnterpriseDto enterpriseDto);
}
