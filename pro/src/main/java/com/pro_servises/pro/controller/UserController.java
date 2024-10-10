package com.pro_servises.pro.controller;

import com.pro_servises.pro.model.Person;
import com.pro_servises.pro.serviceImp.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Person> getUserProfile(@PathVariable Integer id) {
        Person user = userServiceImpl.getUserProfile(id);
        return ResponseEntity.ok(user);
    }
}
