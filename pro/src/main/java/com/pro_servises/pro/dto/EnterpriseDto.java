package com.pro_servises.pro.dto;

import com.pro_servises.pro.enums.Activity;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EnterpriseDto {

    private Integer enterpriseId;
    private String enterpriseName;
    private String enterpriseDescription;
    private String enterpriseLogo;
    private Activity activity;
    private String phoneNumber;
    private String email;
}
