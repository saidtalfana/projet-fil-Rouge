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
import org.springframework.web.multipart.MultipartFile;


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


    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private Set<Order> order;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    @JsonBackReference
    private Enterprise enterprise;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Rating> rating = new HashSet<>();



}
