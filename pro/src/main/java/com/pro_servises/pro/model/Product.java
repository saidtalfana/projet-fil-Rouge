package com.pro_servises.pro.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pro_servises.pro.enums.Category;
import com.pro_servises.pro.enums.ProductStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Product name is required")
    @Size(max = 100, message = "Product name must not exceed 100 characters")
    private String name;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Category is required")
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull(message = "Product status is required")
    @Enumerated(EnumType.STRING)
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


    @OneToMany(mappedBy = "product")
    @JsonManagedReference(value = "productRatings")
    private Set<Rating> ratings = new HashSet<>();



}
