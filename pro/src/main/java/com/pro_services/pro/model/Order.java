package com.pro_services.pro.model;

import jakarta.persistence.OneToMany;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

public class Order {

    private int orderId;
    private Date orderDate;
    private Time orderTime;
    private int orderQuantity;
    private int orderPrice;
    private int orderTotal;

    @OneToMany(mappedBy = "order")
    private Set<Product> products;

}
