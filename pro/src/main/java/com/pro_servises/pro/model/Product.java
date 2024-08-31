package com.pro_servises.pro.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pro_servises.pro.enums.Category;
import com.pro_servises.pro.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



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
    @NotNull(message = "Title is required")
    @Size(min = 6, message = "name or title must be at least 3 characters long")
    private String name;
    @NotNull(message = "Description is required")
    private String description;
    private Double price;
    private Category category;
    private ProductStatus productStatus;
    private String image;


    @OneToMany(mappedBy = "product")
    private Set<Order> order;

    @ManyToOne
    @JoinColumn(name ="provider_id")
    private Provider provider;

    @OneToMany(mappedBy = "product")
    private Set<Rating> rating = new HashSet<>();



}
