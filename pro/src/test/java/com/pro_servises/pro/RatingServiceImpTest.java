package com.pro_servises.pro;

import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.model.Rating;
import com.pro_servises.pro.model.User;
import com.pro_servises.pro.repository.ProductRepository;
import com.pro_servises.pro.repository.RatingRepository;
import com.pro_servises.pro.repository.UserRepository;
import com.pro_servises.pro.serviceImp.RatingServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RatingServiceImpTest {

    @InjectMocks
    private RatingServiceImp ratingService;

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddRating_UserNotFound() {
        // Given
        Rating rating = new Rating();
        Integer productId = 1;
        Integer userId = 1;

        when(productRepository.findById(productId)).thenReturn(Optional.of(new Product()));
        when(userRepository.findById(userId)).thenReturn(Optional.empty()); // User not found

        // When/Then
        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> ratingService.addRating(rating, productId, userId)
        );
        assertEquals("User id " + userId + " not found", thrown.getMessage());
    }

    @Test
    void testAddRating_ProductNotFound() {
        // Given
        Rating rating = new Rating();
        Integer productId = 1;
        Integer userId = 1;

        when(productRepository.findById(productId)).thenReturn(Optional.empty()); // Product not found
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));

        // When/Then
        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> ratingService.addRating(rating, productId, userId)
        );
        assertEquals("Product id " + productId + " not found", thrown.getMessage());
    }

    @Test
    void testUpdateRating_RatingNotFound() {
        // Given
        Rating rating = new Rating();
        Integer id = 1;

        when(ratingRepository.findById(id)).thenReturn(Optional.empty()); // Rating not found

        // When/Then
        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> ratingService.updateRating(rating, id)
        );
        assertEquals("Rating id " + id + " not found", thrown.getMessage());
    }
}
