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
    void testAddRating() {
        // Given
        Integer productId = 1;
        Integer userId = 1;
        Rating rating = new Rating();
        rating.setStars(5);
        rating.setComment("Great product!");

        Product product = new Product();
        User user = new User();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(ratingRepository.save(rating)).thenReturn(rating);

        // When
        Rating result = ratingService.addRating(rating, productId, userId);

        // Then
        assertNotNull(result);
        assertEquals(5, result.getStars());
        verify(productRepository).findById(productId);
        verify(userRepository).findById(userId);
        verify(ratingRepository).save(rating);
    }

    @Test
    void testAddRating_ProductNotFound() {
        // Given
        Integer productId = 1;
        Integer userId = 1;
        Rating rating = new Rating();

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // When/Then
        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> ratingService.addRating(rating, productId, userId)
        );
        assertEquals("Product id 1 not found", thrown.getMessage());
    }

    @Test
    void testAddRating_UserNotFound() {
        // Given
        Integer productId = 1;
        Integer userId = 1;
        Rating rating = new Rating();

        when(productRepository.findById(productId)).thenReturn(Optional.of(new Product()));
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When/Then
        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> ratingService.addRating(rating, productId, userId)
        );
        assertEquals("User id 1 not found", thrown.getMessage());
    }

    @Test
    void testUpdateRating() {
        // Given
        Integer ratingId = 1;
        Rating existingRating = new Rating();
        existingRating.setStars(3);
        existingRating.setComment("Okay product.");

        Rating updatedRating = new Rating();
        updatedRating.setStars(4);
        updatedRating.setComment("Better than okay.");

        when(ratingRepository.findById(ratingId)).thenReturn(Optional.of(existingRating));
        when(ratingRepository.save(existingRating)).thenReturn(updatedRating);

        // When
        Rating result = ratingService.updateRating(updatedRating, ratingId);

        // Then
        assertNotNull(result);
        assertEquals(4, result.getStars());
        verify(ratingRepository).findById(ratingId);
        verify(ratingRepository).save(existingRating);
    }

    @Test
    void testUpdateRating_NotFound() {
        // Given
        Integer ratingId = 1;
        Rating updatedRating = new Rating();

        when(ratingRepository.findById(ratingId)).thenReturn(Optional.empty());

        // When/Then
        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> ratingService.updateRating(updatedRating, ratingId)
        );
        assertEquals("Rating id 1 not found", thrown.getMessage());
    }

    @Test
    void testDeleteRating() {
        // Given
        Integer ratingId = 1;
        when(ratingRepository.existsById(ratingId)).thenReturn(true);

        // When
        ratingService.deleteRating(ratingId);

        // Then
        verify(ratingRepository).deleteById(ratingId);
    }

    @Test
    void testDeleteRating_NotFound() {
        // Given
        Integer ratingId = 1;
        when(ratingRepository.existsById(ratingId)).thenReturn(false);

        // When/Then
        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> ratingService.deleteRating(ratingId)
        );
        assertEquals("Rating id 1 not found", thrown.getMessage());
    }
}
