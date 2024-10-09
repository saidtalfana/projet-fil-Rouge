package com.pro_servises.pro.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommandDTO {

    private String commandId;
    private String name;
    private Double price;
    private String orderDate;
    private String address;
    private String email;

}
