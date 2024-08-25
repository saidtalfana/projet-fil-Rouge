package com.pro_servises.pro.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@DiscriminatorValue("USER")
public class User extends Person{



    @OneToMany(mappedBy = "user")
    private Set<Order> orders;
}
