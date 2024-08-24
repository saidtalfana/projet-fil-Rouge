package com.pro_servises.pro.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rating {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    public Long ratingId;
    private int stars;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product product;
}
