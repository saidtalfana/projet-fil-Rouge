package com.pro_servises.pro.service;

import com.pro_servises.pro.model.Rating;

public interface RatingService {

    Rating addRating(Rating rating,Integer productId , Integer userId);

    Rating updateRating(Rating rating,Integer id);


}
