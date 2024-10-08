package com.pro_servises.pro.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductReportDTO {
    private String productName;
    private String description;
    private Double price;
    private String category;
    private String status;
    private Long orderCount;
}
