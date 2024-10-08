package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.model.Rating;
import com.pro_servises.pro.model.User;
import com.pro_servises.pro.repository.ProductRepository;
import com.pro_servises.pro.repository.RatingRepository;
import com.pro_servises.pro.repository.UserRepository;
import com.pro_servises.pro.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImp implements RatingService {

    private final RatingRepository ratingRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public RatingServiceImp(RatingRepository ratingRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Rating addRating(Rating rating, Integer productId, Integer userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product id " + productId + " not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User id " + userId + " not found"));

        rating.setProduct(product);
        rating.setUser(user);

        return ratingRepository.save(rating);
    }

    public List<Rating> getRatingsByProductId(Integer productId) {
        return ratingRepository.findByProduct_ProductId(productId);
    }

    @Override
    public Rating updateRating(Rating rating, Integer id) {
        Rating existingRating = ratingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rating id " + id + " not found"));

        existingRating.setComment(rating.getComment());
        existingRating.setStars(rating.getStars());

        return ratingRepository.save(existingRating);
    }


    public void deleteRating(Integer id) {
        if (!ratingRepository.existsById(id)) {
            throw new NotFoundException("Rating id " + id + " not found");
        }
        ratingRepository.deleteById(id);
    }
}
