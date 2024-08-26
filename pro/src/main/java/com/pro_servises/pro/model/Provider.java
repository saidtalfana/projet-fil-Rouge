package com.pro_servises.pro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@DiscriminatorValue("PROVIDER")
public class Provider extends Person{

    private String logo;
    private Date startDate;
    private Activity activity;
    private String description;


    @OneToMany(mappedBy = "provider")
    @JsonIgnore
    private Set<Product> products ;
}
