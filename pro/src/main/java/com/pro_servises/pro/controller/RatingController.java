package com.pro_servises.pro.controller;

import com.pro_servises.pro.model.Rating;
import com.pro_servises.pro.service.RatingService;
import com.pro_servises.pro.serviceImp.RatingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    private RatingServiceImp ratingServiceImp;

    @PostMapping("/add_rating")
    public Rating addRating(@RequestBody Rating rating,@RequestParam Integer product_id ) {
        return ratingServiceImp.addRating(rating,product_id);
    }



    @PutMapping("/update_rating/{id}")
    public Rating updateRating( @RequestBody Rating rating,@PathVariable Integer id) {
        return ratingServiceImp.updateRating(rating,id);
    }
}
