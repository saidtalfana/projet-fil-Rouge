package com.pro_servises.pro;

import com.pro_servises.pro.dto.EnterpriseDto;
import com.pro_servises.pro.mapper.EnterpriseMapper;
import com.pro_servises.pro.model.Enterprise;
import com.pro_servises.pro.model.Provider;
import com.pro_servises.pro.repository.EntepriseRepository;
import com.pro_servises.pro.repository.ProviderRepository;
import com.pro_servises.pro.serviceImp.EnterpriseServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EnterpriseServiceImpTest {

    @InjectMocks
    private EnterpriseServiceImp enterpriseService;

    @Mock
    private EntepriseRepository enterpriseRepository;

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private EnterpriseMapper enterpriseMapper;

    private EnterpriseDto enterpriseDto;
    private Enterprise enterprise;
    private Provider provider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        enterpriseDto = new EnterpriseDto();
        enterpriseDto.setEnterpriseId(1);
        enterpriseDto.setEnterpriseName("Talfana Enterprises");
        enterpriseDto.setEnterpriseDescription("A description of Talfana Enterprises");
        enterpriseDto.setEnterpriseLogo("logo.png");
        enterpriseDto.setActivity("Various Activities");
        enterpriseDto.setPhoneNumber("1234567890");
        enterpriseDto.setEmail("contact@talfana.com");

        provider = new Provider(); // Ensure you have the correct constructor or methods
        provider.setId(1); // Assuming there's a method to set the provider ID

        enterprise = new Enterprise();
        enterprise.setEnterpriseId(1);
        enterprise.setEnterpriseName("Talfana Enterprises");
        enterprise.setEnterpriseDescription("A description of Talfana Enterprises");
        enterprise.setEnterpriseLogo("logo.png");
        enterprise.setActivity("Various Activities");
        enterprise.setPhoneNumber("1234567890");
        enterprise.setEmail("contact@talfana.com");
        enterprise.setProvider(provider);
    }

    @Test
    void addEnterprise_shouldSaveEnterpriseAndReturnDto() {
        when(enterpriseMapper.mapToEnterprise(enterpriseDto)).thenReturn(enterprise);
        when(providerRepository.findById(1)).thenReturn(Optional.of(provider));
        when(enterpriseRepository.save(any(Enterprise.class))).thenReturn(enterprise);
        when(enterpriseMapper.mapToEnterpriseDto(any(Enterprise.class))).thenReturn(enterpriseDto);

        EnterpriseDto result = enterpriseService.addEnterprise(enterpriseDto, 1);

        assertNotNull(result);
        assertEquals(enterpriseDto.getEnterpriseId(), result.getEnterpriseId());
        verify(enterpriseRepository, times(1)).save(any(Enterprise.class));
    }

    @Test
    void getEnterpriseById_shouldReturnEnterpriseDto() {
        when(enterpriseRepository.findByProviderId(1)).thenReturn(enterprise);
        when(enterpriseMapper.mapToEnterpriseDto(any(Enterprise.class))).thenReturn(enterpriseDto);

        EnterpriseDto result = enterpriseService.getEnterpriseById(1);

        assertNotNull(result);
        assertEquals(enterpriseDto.getEnterpriseId(), result.getEnterpriseId());
    }

    @Test
    void updateEnterprise_shouldUpdateAndReturnUpdatedDto() {
        when(enterpriseRepository.findById(1)).thenReturn(Optional.of(enterprise));
        when(enterpriseRepository.save(any(Enterprise.class))).thenReturn(enterprise);
        when(enterpriseMapper.mapToEnterpriseDto(any(Enterprise.class))).thenReturn(enterpriseDto);

        enterpriseDto.setEnterpriseName("Updated Name");
        EnterpriseDto result = enterpriseService.updateEnterprise(enterpriseDto);

        assertNotNull(result);
        assertEquals("Updated Name", result.getEnterpriseName());
    }

    @Test
    void getEnterprise_shouldReturnEnterpriseDto() {
        when(enterpriseRepository.findById(1)).thenReturn(Optional.of(enterprise));
        when(enterpriseMapper.mapToEnterpriseDto(any(Enterprise.class))).thenReturn(enterpriseDto);

        EnterpriseDto result = enterpriseService.getEnterprise(1);

        assertNotNull(result);
        assertEquals(enterpriseDto.getEnterpriseId(), result.getEnterpriseId());
    }
}
