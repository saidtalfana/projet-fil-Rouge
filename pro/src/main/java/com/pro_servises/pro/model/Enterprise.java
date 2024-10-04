package com.pro_servises.pro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pro_servises.pro.enums.Activity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Enterprise {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer enterpriseId;
    private String enterpriseName;
    private String enterpriseDescription;
    private String enterpriseLogo;
    private Activity activity;
    private String phoneNumber;
    private String email;


    @ManyToOne
    @JoinColumn(name = "provider_id")
    @JsonIgnore
    private Provider provider;


    @OneToMany(mappedBy = "enterprise")
    @JsonIgnore
    private Set<Product> products;

}
