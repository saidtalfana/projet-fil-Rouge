package com.pro_servises.pro.dto;

import com.pro_servises.pro.enums.Activity;
import lombok.Data;

@Data
public class EnterpriseDto {

    private Integer enterpriseId;
    private String enterpriseName;
    private String enterpriseDescription;
    private String enterpriseLogo;
    private Activity activity;
}
