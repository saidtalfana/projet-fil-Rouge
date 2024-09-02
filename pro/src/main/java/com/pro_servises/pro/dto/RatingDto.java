package com.pro_servises.pro.dto;

import lombok.Data;

@Data
public class RatingDto {


    public Integer ratingId;
    private int stars;
    private String comment;
}
