package com.pro_servises.pro.dto;

import com.pro_servises.pro.enums.Category;
import com.pro_servises.pro.enums.ProductStatus;
import jakarta.persistence.Transient;
import lombok.*;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Integer productId;
    private String name;
    private String description;
    private Double price;
    private Category category; // Consider using category ID instead of the whole object
    private ProductStatus productStatus;

    @Transient
    private byte[] image; // This field will not be persisted

}