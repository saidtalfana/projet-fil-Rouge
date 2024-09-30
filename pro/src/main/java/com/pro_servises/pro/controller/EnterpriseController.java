package com.pro_servises.pro.controller;

import com.pro_servises.pro.dto.EnterpriseDto;
import com.pro_servises.pro.model.Enterprise;
import com.pro_servises.pro.serviceImp.EnterpriseServiceImp;
import com.pro_servises.pro.serviceImp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseController {
@Autowired
private EnterpriseServiceImp enterpriseServiceImp;
    @Autowired
    private ProductServiceImp productServiceImp;


    @PostMapping("/add_enterprise")
 EnterpriseDto addEnterprise(@RequestBody EnterpriseDto enterpriseDto,@RequestParam Integer provider_id){
        return enterpriseServiceImp.addEnterprise(enterpriseDto, provider_id);
    }

 @GetMapping("/get_enterprise/{provider_id}")
    EnterpriseDto getEnterpriseById(@PathVariable Integer provider_id){
     return enterpriseServiceImp.getEnterpriseById(provider_id);
 }

 @PutMapping("/update_enterprise")
    EnterpriseDto updateEnterprise(@RequestBody EnterpriseDto enterpriseDto){
     return enterpriseServiceImp.updateEnterprise(enterpriseDto);
    }
    @GetMapping("/get_enterprise")
    EnterpriseDto getEnterprise(@RequestParam Integer enterprise_id){
     return enterpriseServiceImp.getEnterprise(enterprise_id);
    }


    @GetMapping("/enterprise-by-product/{id}")
    public Enterprise getEnterpriseByProductId(@PathVariable Integer id) {
        return productServiceImp.getEnterpriseByProductId(id);
    }
}
