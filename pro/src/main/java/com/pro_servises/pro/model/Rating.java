package com.pro_servises.pro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ratingId;

    @Min(1)
    @Max(5)
    private int stars;

    private String comment;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "userReference") // Unique reference name
    private User user;



    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "productRatings")
    private Product product;


}
