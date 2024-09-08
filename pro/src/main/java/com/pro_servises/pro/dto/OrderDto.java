package com.pro_servises.pro.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class OrderDto {

    private Integer orderId;
    private Date orderDate;
    private Time orderTime;
    private String name;
    private String address;
    private String email;
    private Integer phoneNumber;
    private String customerRequest;


}
