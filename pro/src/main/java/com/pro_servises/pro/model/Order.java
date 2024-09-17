package com.pro_servises.pro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CustomerOrder")
public class Order {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer orderId;
    private Date orderDate;
    private Time orderTime;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private String customerRequest;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
