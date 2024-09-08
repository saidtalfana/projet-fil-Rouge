package com.pro_servises.pro.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Person {



    @OneToMany(mappedBy = "admin")
    private Set<Article> articles;
}
