package com.pro_services.pro.model;

import com.pro_services.pro.enums.Activity;
import jakarta.persistence.OneToMany;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Entrepreneur extends Person{

    private String logo;
    private Date startDate;
    private Activity activity;
    private String description;


    @OneToMany(mappedBy = "entrepreneur")
    private Set<Product> products = new HashSet<>();
}
