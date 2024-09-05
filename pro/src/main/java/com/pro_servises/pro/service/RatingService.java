package com.pro_servises.pro.service;

import com.pro_servises.pro.model.Rating;

public interface RatingService {

    Rating addRating(Rating rating,Integer product_id , Integer user_id);

    Rating updateRating(Rating rating,Integer id);


}
