package com.pro_servises.pro.controller;

import com.pro_servises.pro.dto.EnterpriseDto;
import com.pro_servises.pro.model.Enterprise;
import com.pro_servises.pro.serviceImp.EnterpriseServiceImp;
import com.pro_servises.pro.serviceImp.ProductServiceImp;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseController {

    private final EnterpriseServiceImp enterpriseServiceImp;
    private final ProductServiceImp productServiceImp;

    public EnterpriseController(EnterpriseServiceImp enterpriseServiceImp, ProductServiceImp productServiceImp) {
        this.enterpriseServiceImp = enterpriseServiceImp;
        this.productServiceImp = productServiceImp;
    }

    /**
     * Add a new enterprise
     *
     * @param enterpriseDto the enterprise data to be added
     * @param providerId the ID of the provider
     * @return the created EnterpriseDto object
     */
    @PostMapping("/add_enterprise")
    public EnterpriseDto addEnterprise(@RequestBody EnterpriseDto enterpriseDto, @RequestParam Integer providerId) {
        return enterpriseServiceImp.addEnterprise(enterpriseDto, providerId);
    }

    /**
     * Get an enterprise by provider ID
     *
     * @param providerId the ID of the provider
     * @return the corresponding EnterpriseDto object
     */
    @GetMapping("/get_enterprise/{providerId}")
    public EnterpriseDto getEnterpriseById(@PathVariable Integer providerId) {
        return enterpriseServiceImp.getEnterpriseById(providerId);
    }

    /**
     * Update an existing enterprise
     *
     * @param enterpriseDto the updated enterprise data
     * @return the updated EnterpriseDto object
     */
    @PutMapping("/update_enterprise")
    public EnterpriseDto updateEnterprise(@RequestBody EnterpriseDto enterpriseDto) {
        return enterpriseServiceImp.updateEnterprise(enterpriseDto);
    }

    /**
     * Get an enterprise by enterprise ID
     *
     * @param enterpriseId the ID of the enterprise
     * @return the corresponding EnterpriseDto object
     */
    @GetMapping("/get_enterprise")
    public EnterpriseDto getEnterprise(@RequestParam Integer enterpriseId) {
        return enterpriseServiceImp.getEnterprise(enterpriseId);
    }

    /**
     * Get an enterprise by product ID
     *
     * @param id the ID of the product
     * @return the corresponding Enterprise object
     */
    @GetMapping("/enterprise-by-product/{id}")
    public Enterprise getEnterpriseByProductId(@PathVariable Integer id) {
        return productServiceImp.getEnterpriseByProductId(id);
    }
}
