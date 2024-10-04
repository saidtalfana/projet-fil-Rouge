package com.pro_servises.pro;

import com.pro_servises.pro.dto.EnterpriseDto;
import com.pro_servises.pro.mapper.EnterpriseMapper;
import com.pro_servises.pro.model.Enterprise;
import com.pro_servises.pro.model.Provider;
import com.pro_servises.pro.repository.EntepriseRepository;
import com.pro_servises.pro.repository.ProviderRepository;
import com.pro_servises.pro.service.EnterpriseService;
import com.pro_servises.pro.serviceImp.EnterpriseServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EnterpriseServiceImpTest {

    @InjectMocks
    private EnterpriseServiceImp enterpriseService;

    @Mock
    private EntepriseRepository enterpriseRepository;

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private EnterpriseMapper enterpriseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddEnterprise() {
        // Given
        EnterpriseDto enterpriseDto = new EnterpriseDto();
        Enterprise enterprise = new Enterprise();
        Provider provider = new Provider();
        Enterprise savedEnterprise = new Enterprise();
        savedEnterprise.setEnterpriseId(1); // Assurez-vous que votre Enterprise a une méthode pour définir son ID
        EnterpriseDto savedEnterpriseDto = new EnterpriseDto();

        when(enterpriseMapper.mapToEnterprise(enterpriseDto)).thenReturn(enterprise);
        when(providerRepository.findById(1)).thenReturn(Optional.of(provider));
        when(enterpriseRepository.save(enterprise)).thenReturn(savedEnterprise);
        when(enterpriseMapper.mapToEnterpriseDto(savedEnterprise)).thenReturn(savedEnterpriseDto);

        // When
        EnterpriseDto result = enterpriseService.addEnterprise(enterpriseDto, 1);

        // Then
        assertNotNull(result);
        assertEquals(savedEnterpriseDto, result);
        verify(enterpriseRepository, times(1)).save(enterprise);
        verify(providerRepository, times(1)).findById(1);
        verify(enterpriseMapper, times(1)).mapToEnterprise(enterpriseDto);
        verify(enterpriseMapper, times(1)).mapToEnterpriseDto(savedEnterprise);
    }

    @Test
    void testGetEnterpriseById() {
        // Given
        Integer providerId = 1;
        Enterprise enterprise = new Enterprise();
        EnterpriseDto enterpriseDto = new EnterpriseDto();

        when(enterpriseRepository.findByProviderId(providerId)).thenReturn(enterprise);
        when(enterpriseMapper.mapToEnterpriseDto(enterprise)).thenReturn(enterpriseDto);

        // When
        EnterpriseDto result = enterpriseService.getEnterpriseById(providerId);

        // Then
        assertNotNull(result);
        assertEquals(enterpriseDto, result);
        verify(enterpriseRepository, times(1)).findByProviderId(providerId);
        verify(enterpriseMapper, times(1)).mapToEnterpriseDto(enterprise);
    }

    @Test
    void testUpdateEnterprise() {
        // Given
        EnterpriseDto enterpriseDto = new EnterpriseDto();
        Enterprise existingEnterprise = new Enterprise();
        Enterprise updatedEnterprise = new Enterprise();
        updatedEnterprise.setEnterpriseId(1);

        when(enterpriseRepository.findById(enterpriseDto.getEnterpriseId())).thenReturn(Optional.of(existingEnterprise));
        when(enterpriseRepository.save(existingEnterprise)).thenReturn(updatedEnterprise);
        when(enterpriseMapper.mapToEnterpriseDto(updatedEnterprise)).thenReturn(enterpriseDto);

        // When
        EnterpriseDto result = enterpriseService.updateEnterprise(enterpriseDto);

        // Then
        assertNotNull(result);
        assertEquals(enterpriseDto.getEnterpriseId(), result.getEnterpriseId());
        verify(enterpriseRepository, times(1)).save(existingEnterprise);
        verify(enterpriseRepository, times(1)).findById(enterpriseDto.getEnterpriseId());
        verify(enterpriseMapper, times(1)).mapToEnterpriseDto(updatedEnterprise);
    }

    @Test
    void testGetEnterprise() {
        // Given
        Integer enterpriseId = 1;
        Enterprise enterprise = new Enterprise();
        EnterpriseDto enterpriseDto = new EnterpriseDto();

        when(enterpriseRepository.findById(enterpriseId)).thenReturn(Optional.of(enterprise));
        when(enterpriseMapper.mapToEnterpriseDto(enterprise)).thenReturn(enterpriseDto);

        // When
        EnterpriseDto result = enterpriseService.getEnterprise(enterpriseId);

        // Then
        assertNotNull(result);
        assertEquals(enterpriseDto, result);
        verify(enterpriseRepository, times(1)).findById(enterpriseId);
        verify(enterpriseMapper, times(1)).mapToEnterpriseDto(enterprise);
    }
}
