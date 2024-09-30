package com.pro_servises.pro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pro_servises.pro.enums.OrderStatus;
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
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
