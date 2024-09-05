package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.model.Rating;
import com.pro_servises.pro.model.User;
import com.pro_servises.pro.repository.ProductRepository;
import com.pro_servises.pro.repository.RatingRepository;
import com.pro_servises.pro.repository.UserRepository;
import com.pro_servises.pro.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImp implements RatingService {



    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Rating addRating(Rating rating, Integer product_id, Integer user_id) {
        Product product = productRepository.findById(product_id).get();
        User user = userRepository.findById(user_id).get();
        rating.setProduct(product);
        rating.setUser(user);

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
