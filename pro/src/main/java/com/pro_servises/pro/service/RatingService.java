package com.pro_servises.pro.service;

import com.pro_servises.pro.model.Rating;

import java.util.List;

public interface RatingService {

    Rating addRating(Rating rating,Integer productId , Integer userId);

    Rating updateRating(Rating rating,Integer id);

    List<Rating> getRatingsByProductId(Integer productId);

    void deleteRating(Integer id);


}
