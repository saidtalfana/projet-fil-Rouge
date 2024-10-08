package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.EnterpriseDto;
import com.pro_servises.pro.mapper.EnterpriseMapper;
import com.pro_servises.pro.model.Enterprise;
import com.pro_servises.pro.model.Provider;
import com.pro_servises.pro.repository.EntepriseRepository;
import com.pro_servises.pro.repository.ProviderRepository;
import com.pro_servises.pro.service.EnterpriseService;
import org.springframework.stereotype.Service;


@Service
public class EnterpriseServiceImp implements EnterpriseService {

    private final EntepriseRepository enterpriseRepository;
    private final ProviderRepository providerRepository;
    private final EnterpriseMapper enterpriseMapper;

    public EnterpriseServiceImp(EntepriseRepository enterpriseRepository, ProviderRepository providerRepository, EnterpriseMapper enterpriseMapper) {
        this.enterpriseRepository = enterpriseRepository;
        this.providerRepository = providerRepository;
        this.enterpriseMapper = enterpriseMapper;
    }

    @Override
    public EnterpriseDto addEnterprise(EnterpriseDto enterpriseDto, Integer providerId) {

        Enterprise enterprise = enterpriseMapper.mapToEnterprise(enterpriseDto);
       Provider provider = providerRepository.findById(providerId).get();
       enterprise.setProvider(provider);
       Enterprise savedEnterprise = enterpriseRepository.save(enterprise);
       return enterpriseMapper.mapToEnterpriseDto(savedEnterprise);

    }

    @Override
    public EnterpriseDto getEnterpriseById(Integer providerId) {
 Enterprise enterprise = enterpriseRepository.findByProviderId(providerId);
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
@Override
    public EnterpriseDto getEnterprise(Integer enterpriseId) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId).get();
        return enterpriseMapper.mapToEnterpriseDto(enterprise);
    }
}
