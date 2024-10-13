package com.pro_servises.pro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.sql.Date;
import java.sql.Time;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CustomerOrder")
public class Order {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer orderId;
    @NotBlank(message = "Order date is required")
    private Date orderDate;

    @NotBlank(message = "Order time is required")
    private Time orderTime;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Address is required")
    @Size(max = 250, message = "Address must not exceed 250 characters")
    private String address;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 15, message = "Phone number must not exceed 15 characters")
    private String phoneNumber;

    @Size(max = 500, message = "Customer request must not exceed 500 characters")
    private String customerRequest;

    @ManyToOne
    @JoinColumn(name = "service_id")
    @JsonBackReference(value = "productOrders")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "userOrders")
    private User user;

}
