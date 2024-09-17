package com.pro_servises.pro.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class ContactDto {

    private Integer contactId;
    private String name;
    private String email;
    private Date date;
    private Time time;
    private String phone;
    private String message;
}
