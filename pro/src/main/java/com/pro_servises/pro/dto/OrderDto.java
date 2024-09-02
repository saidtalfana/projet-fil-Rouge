package com.pro_servises.pro.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class OrderDto {

    private Integer orderId;
    private Date orderDate;
    private Time orderTime;
    private Long orderQuantity;
    private Float orderPrice;
    private Float orderTotal;


}
