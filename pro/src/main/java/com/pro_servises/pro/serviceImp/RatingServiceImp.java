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

/**
 * Implementation of the {@link RatingService} interface for managing ratings.
 */
@Service
public class RatingServiceImp implements RatingService {

    private final RatingRepository ratingRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    /**
     * Constructs a {@link RatingServiceImp} instance.
     *
     * @param ratingRepository the repository for managing ratings
     * @param productRepository the repository for managing products
     * @param userRepository the repository for managing users
     */
    public RatingServiceImp(RatingRepository ratingRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    /**
     * Adds a new rating for a product by a user.
     *
     * @param rating the rating to be added
     * @param productId the ID of the product being rated
     * @param userId the ID of the user adding the rating
     * @return the saved rating
     * @throws NotFoundException if the product or user is not found
     */
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

    /**
     * Retrieves all ratings for a specific product.
     *
     * @param productId the ID of the product
     * @return a list of ratings for the product
     */
    public List<Rating> getRatingsByProductId(Integer productId) {
        return ratingRepository.findByProduct_ProductId(productId);
    }

    /**
     * Updates an existing rating.
     *
     * @param rating the updated rating
     * @param id the ID of the rating to be updated
     * @return the updated rating
     * @throws NotFoundException if the rating is not found
     */
    @Override
    public Rating updateRating(Rating rating, Integer id) {
        Rating existingRating = ratingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rating id " + id + " not found"));

        existingRating.setComment(rating.getComment());
        existingRating.setStars(rating.getStars());

        return ratingRepository.save(existingRating);
    }

    /**
     * Deletes a rating by its ID.
     *
     * @param id the ID of the rating to be deleted
     * @throws NotFoundException if the rating is not found
     */
    public void deleteRating(Integer id) {
        if (!ratingRepository.existsById(id)) {
            throw new NotFoundException("Rating id " + id + " not found");
        }
        ratingRepository.deleteById(id);
    }
}
