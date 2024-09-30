package com.pro_servises.pro.controller;

import com.pro_servises.pro.model.Rating;
import com.pro_servises.pro.service.RatingService;
import com.pro_servises.pro.serviceImp.RatingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    private RatingServiceImp ratingServiceImp;

    @PostMapping("/add")
    public ResponseEntity<Rating> submitRating(@RequestBody Rating rating) {
        Rating savedRating = ratingServiceImp.addRat(rating);
        return ResponseEntity.ok(savedRating);
    }

    @GetMapping("/get_rating_by_product/{productId}")
    public ResponseEntity<List<Rating>> getRatingsByProduct(@PathVariable Integer productId) {
        List<Rating> ratings = ratingServiceImp.getRatingsByProductId(productId);
        return ResponseEntity.ok(ratings);
    }

    @PostMapping("/add_rating")
    public Rating addRating(@RequestBody Rating rating,@RequestParam Integer product_id , @RequestParam Integer user_id) {
        return ratingServiceImp.addRating(rating,product_id,user_id);
    }
    @GetMapping("/get_all_rating_by_product_id/{id}")
    public Rating updateRating( @RequestBody Rating rating,@PathVariable Integer id) {
        return ratingServiceImp.updateRating(rating,id);
    }



}
