package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.EnterpriseDto;
import com.pro_servises.pro.mapper.EnterpriseMapper;
import com.pro_servises.pro.model.Enterprise;
import com.pro_servises.pro.model.Provider;
import com.pro_servises.pro.repository.EntepriseRepository;
import com.pro_servises.pro.repository.ProviderRepository;
import com.pro_servises.pro.service.EnterpriseService;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link EnterpriseService} interface for managing enterprises.
 */
@Service
public class EnterpriseServiceImp implements EnterpriseService {

    private final EntepriseRepository enterpriseRepository;
    private final ProviderRepository providerRepository;
    private final EnterpriseMapper enterpriseMapper;

    /**
     * Constructs a {@link EnterpriseServiceImp} instance.
     *
     * @param enterpriseRepository the repository for managing enterprises
     * @param providerRepository the repository for managing providers
     * @param enterpriseMapper the mapper for converting between Enterprise and EnterpriseDto
     */
    public EnterpriseServiceImp(EntepriseRepository enterpriseRepository, ProviderRepository providerRepository, EnterpriseMapper enterpriseMapper) {
        this.enterpriseRepository = enterpriseRepository;
        this.providerRepository = providerRepository;
        this.enterpriseMapper = enterpriseMapper;
    }

    /**
     * Adds a new enterprise associated with a provider.
     *
     * @param enterpriseDto the enterprise data transfer object to be added
     * @param providerId the ID of the provider associated with the enterprise
     * @return the saved enterprise as a data transfer object
     */
    @Override
    public EnterpriseDto addEnterprise(EnterpriseDto enterpriseDto, Integer providerId) {
        Enterprise enterprise = enterpriseMapper.mapToEnterprise(enterpriseDto);
        Provider provider = providerRepository.findById(providerId).get();
        enterprise.setProvider(provider);
        Enterprise savedEnterprise = enterpriseRepository.save(enterprise);
        return enterpriseMapper.mapToEnterpriseDto(savedEnterprise);
    }

    /**
     * Retrieves an enterprise by its associated provider's ID.
     *
     * @param providerId the ID of the provider
     * @return the enterprise as a data transfer object
     */
    @Override
    public EnterpriseDto getEnterpriseById(Integer providerId) {
        Enterprise enterprise = enterpriseRepository.findByProviderId(providerId);
        return enterpriseMapper.mapToEnterpriseDto(enterprise);
    }

    /**
     * Updates an existing enterprise.
     *
     * @param enterpriseDto the updated enterprise data transfer object
     * @return the updated enterprise as a data transfer object
     */
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

    /**
     * Retrieves an enterprise by its ID.
     *
     * @param enterpriseId the ID of the enterprise
     * @return the enterprise as a data transfer object
     */
    @Override
    public EnterpriseDto getEnterprise(Integer enterpriseId) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId).get();
        return enterpriseMapper.mapToEnterpriseDto(enterprise);
    }
}
