package com.pro_servises.pro.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Person {

}
