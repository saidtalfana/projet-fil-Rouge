package com.pro_servises.pro.controller;

import com.pro_servises.pro.model.Rating;
import com.pro_servises.pro.serviceImp.RatingServiceImp;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    private final RatingServiceImp ratingServiceImp;

    public RatingController(RatingServiceImp ratingServiceImp) {
        this.ratingServiceImp = ratingServiceImp;
    }

    /**
     * Add a new rating
     * @param rating the rating to be added
     * @param productId the ID of the product
     * @param userId the ID of the user
     * @return the created Rating object
     */
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Rating addRating(
            @RequestBody Rating rating,
            @RequestParam Integer productId,
            @RequestParam Integer userId) {
        Rating createdRating = ratingServiceImp.addRating(rating, productId, userId);
        return createdRating;
    }

    /**
     * Get ratings for a specific product
     * @param productId the ID of the product
     * @return list of ratings for the product
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getRatingsByProductId(@PathVariable Integer productId) {
        List<Rating> ratings = ratingServiceImp.getRatingsByProductId(productId);
        return ResponseEntity.ok(ratings);
    }

    /**
     * Update an existing rating
     * @param id the ID of the rating to be updated
     * @param rating the updated rating data
     * @return the updated Rating object
     */
    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable Integer id, @RequestBody Rating rating) {
        Rating updatedRating = ratingServiceImp.updateRating(rating, id);
        return ResponseEntity.ok(updatedRating);
    }

    /**
     * Delete a rating by ID
     * @param id the ID of the rating to be deleted
     * @return HTTP status no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Integer id) {
        ratingServiceImp.deleteRating(id);
        return ResponseEntity.noContent().build();
    }
}
