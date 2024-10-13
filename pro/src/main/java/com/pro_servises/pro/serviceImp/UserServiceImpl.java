package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.SignupRequest;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.model.Person;
import com.pro_servises.pro.repository.UserRepository;
import com.pro_servises.pro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link UserService} interface for managing user operations.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves the user profile by ID.
     *
     * @param id the ID of the user
     * @return the user's profile as a {@link Person} object
     * @throws NotFoundException if no user is found with the given ID
     */
    public Person getUserProfile(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }
}
