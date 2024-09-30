package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.model.Rating;
import com.pro_servises.pro.model.User;
import com.pro_servises.pro.repository.ProductRepository;
import com.pro_servises.pro.repository.RatingRepository;
import com.pro_servises.pro.repository.UserRepository;
import com.pro_servises.pro.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImp implements RatingService {



    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;



    public Rating addRat(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> getRatingsByProductId(Integer productId) {
        return ratingRepository.findByProductId(productId);
    }



    @Override
    public Rating addRating(Rating rating, Integer product_id, Integer user_id) {
        Product product = productRepository.findById(product_id)
                .orElseThrow(() -> new NotFoundException("Product id " + product_id + " not found"));
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new NotFoundException("User id " + user_id + " not found"));

        rating.setProduct(product);
        rating.setUser(user);

        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Rating rating, Integer id) {
        Rating existingRating = ratingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rating id " + id + " not found"));

        existingRating.setComment(rating.getComment());
        existingRating.setStars(rating.getStars());

        return ratingRepository.save(existingRating);
    }



}
