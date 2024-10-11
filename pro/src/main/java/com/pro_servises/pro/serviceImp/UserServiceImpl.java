package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.SignupRequest;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.model.Person;
import com.pro_servises.pro.model.User;
import com.pro_servises.pro.repository.UserRepository;
import com.pro_servises.pro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


@Autowired
private UserRepository userRepository;

    public Person getUserProfile(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }

}
