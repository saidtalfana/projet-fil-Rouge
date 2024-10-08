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


    @PostMapping("/add_enterprise")
 EnterpriseDto addEnterprise(@RequestBody EnterpriseDto enterpriseDto,@RequestParam Integer providerId){
        return enterpriseServiceImp.addEnterprise(enterpriseDto, providerId);
    }

 @GetMapping("/get_enterprise/{providerId}")
    EnterpriseDto getEnterpriseById(@PathVariable Integer providerId){
     return enterpriseServiceImp.getEnterpriseById(providerId);
 }

 @PutMapping("/update_enterprise")
    EnterpriseDto updateEnterprise(@RequestBody EnterpriseDto enterpriseDto){
     return enterpriseServiceImp.updateEnterprise(enterpriseDto);
    }
    @GetMapping("/get_enterprise")
    EnterpriseDto getEnterprise(@RequestParam Integer enterpriseId){
     return enterpriseServiceImp.getEnterprise(enterpriseId);
    }


    @GetMapping("/enterprise-by-product/{id}")
    public Enterprise getEnterpriseByProductId(@PathVariable Integer id) {
        return productServiceImp.getEnterpriseByProductId(id);
    }

}
