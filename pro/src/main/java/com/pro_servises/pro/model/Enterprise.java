package com.pro_servises.pro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class Enterprise {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer enterpriseId;
    @NotBlank(message = "Enterprise name is required")
    @Size(max = 400, message = "Enterprise name must not exceed 200 characters")
    private String enterpriseName;

    @NotBlank(message = "Enterprise description is required")
    @Lob
    @Column(columnDefinition = "TEXT")
    private String enterpriseDescription;

    private String enterpriseLogo;

    @NotBlank(message = "Activity is required")
    @Size(max = 500, message = "Activity must not exceed 100 characters")
    private String activity;

    @NotBlank(message = "Phone number is required")
    @Size(max = 30, message = "Phone number must not exceed 15 characters")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;



    @ManyToOne
    @JoinColumn(name = "provider_id")
    @JsonIgnore
    private Provider provider;


    @OneToMany(mappedBy = "enterprise")
    @JsonIgnore
    private Set<Product> products;

}
