package com.pro_servises.pro.service;

import com.pro_servises.pro.dto.EnterpriseDto;

public interface EnterpriseService {


    EnterpriseDto addEnterprise(EnterpriseDto enterpriseDto, Integer providerId);

    EnterpriseDto getEnterpriseById(Integer providerId);

    EnterpriseDto updateEnterprise(EnterpriseDto enterpriseDto);

    EnterpriseDto getEnterprise(Integer enterpriseId);

}
