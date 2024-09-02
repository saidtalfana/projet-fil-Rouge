package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.model.Rating;
import com.pro_servises.pro.repository.ProductRepository;
import com.pro_servises.pro.repository.RatingRepository;
import com.pro_servises.pro.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImp implements RatingService {



    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Rating addRating(Rating rating, Integer productId) {
        Product product = productRepository.findById(productId).get();

        rating.setProduct(product);

        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Rating rating, Integer id) {
        Rating existingRating =ratingRepository.findById(id).get();
        existingRating.setComment(rating.getComment());
        existingRating.setStars(rating.getStars());

        return ratingRepository.save(existingRating);
    }
}
