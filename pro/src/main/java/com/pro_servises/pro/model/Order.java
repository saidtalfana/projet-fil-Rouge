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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private int orderId;
    private Date orderDate;
    private Time orderTime;
    private int orderQuantity;
    private int orderPrice;
    private int orderTotal;

    @OneToMany(mappedBy = "order")
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
