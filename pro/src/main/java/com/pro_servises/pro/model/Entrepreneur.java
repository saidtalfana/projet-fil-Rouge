package com.pro_servises.pro.model;

import com.pro_servises.pro.enums.Activity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("ENTREPRENEUR")
public class Entrepreneur extends Person{

    private String logo;
    private Date startDate;
    private Activity activity;
    private String description;


    @OneToMany(mappedBy = "entrepreneur")
    private Set<Product> products ;
}
