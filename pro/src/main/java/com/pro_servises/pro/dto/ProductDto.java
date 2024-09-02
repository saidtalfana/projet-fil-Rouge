package com.pro_servises.pro.dto;

import com.pro_servises.pro.enums.Category;
import com.pro_servises.pro.enums.ProductStatus;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Integer productId;
    private String name;
    private String description;
    private Double price;
    private Category category;
    private ProductStatus productStatus;
    private String image;
}
