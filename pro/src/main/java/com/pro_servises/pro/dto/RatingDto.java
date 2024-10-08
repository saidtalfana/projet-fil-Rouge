package com.pro_servises.pro.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingDto {

    private int stars;
    private String comment;
}
