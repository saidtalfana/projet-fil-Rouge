package com.pro_services.pro.model;


import com.pro_services.pro.enums.Category;
import com.pro_services.pro.enums.ProductStatus;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

public class Product {

    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Category category;
    private ProductStatus productStatus;
    private String image;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name ="entrepneur_id")
    private Entrepreneur entrepneur;

    @OneToMany(mappedBy = "product")
    private Set<Rating> rating = new HashSet<>();



}
