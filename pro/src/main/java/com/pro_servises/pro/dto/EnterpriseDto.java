package com.pro_servises.pro.dto;

import lombok.Data;

@Data
public class EnterpriseDto {

    private Integer enterpriseId;
    private String enterpriseName;
    private String enterpriseDescription;
    private String enterpriseLogo;
    private String activity;
    private String phoneNumber;
    private String email;
}
