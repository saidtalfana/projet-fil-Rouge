package com.pro_services.pro.model;

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
public class Customer extends Person{



    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;
}
