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
    public Integer ratingId;
    private int stars;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;
}
