package com.pro_servises.pro.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
public class ContactDto {

    private Integer contactId;
    private String name;
    private String email;
    private Date date;
    private Time time;
    private String phone;
    private String message;
}
