package com.pro_servises.pro.service;

import com.pro_servises.pro.model.Rating;

public interface RatingService {

    Rating addRating(Rating rating,Long productId);

    Rating updateRating(Rating rating,Long id);


}
