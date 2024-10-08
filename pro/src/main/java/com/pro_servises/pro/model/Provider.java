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
@DiscriminatorValue("PROVIDER")
public class Provider extends Person{


    @OneToMany(mappedBy = "provider")
    private Set<Enterprise> enterprises;
}
