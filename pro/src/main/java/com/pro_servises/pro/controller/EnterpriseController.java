package com.pro_servises.pro.controller;

import com.pro_servises.pro.dto.EnterpriseDto;
import com.pro_servises.pro.serviceImp.EnterpriseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseController {
@Autowired
private EnterpriseServiceImp enterpriseServiceImp;


 @PostMapping("/add_enterprise")
 EnterpriseDto addEnterprise(@RequestBody EnterpriseDto enterpriseDto,@RequestParam Integer provider_id){
        return enterpriseServiceImp.addEnterprise(enterpriseDto, provider_id);
    }

 @GetMapping("/get_enterprise/{enterprise_id}")
    EnterpriseDto getEnterpriseById(@PathVariable Integer enterprise_id){
     return enterpriseServiceImp.getEnterpriseById(enterprise_id);
 }

 @PutMapping("/update_enterprise")
    EnterpriseDto updateEnterprise(@RequestBody EnterpriseDto enterpriseDto){
     return enterpriseServiceImp.updateEnterprise(enterpriseDto);
    }
}
