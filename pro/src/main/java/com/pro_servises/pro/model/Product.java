package com.pro_servises.pro.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pro_servises.pro.enums.Category;
import com.pro_servises.pro.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;
    private String name;
    private String description;
    private Double price;
    private Category category;
    private ProductStatus productStatus;

    @Lob
    @Column(length = 1000000) // Adjust length as needed for images
    private byte[] image;




    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    @JsonBackReference
    @JsonIgnore
    private Enterprise enterprise;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference(value = "productOrders") // Ensure this matches the Order side
    private Set<Order> orders = new HashSet<>();

//    @OneToMany(mappedBy = "product")
//    @JsonManagedReference(value = "productRatings") // Ensure this corresponds to the Rating class
//    private Set<Rating> ratings = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @JsonManagedReference(value = "productRatings")
    private Set<Rating> ratings = new HashSet<>();



}
