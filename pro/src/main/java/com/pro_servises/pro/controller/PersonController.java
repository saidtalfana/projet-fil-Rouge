package com.pro_servises.pro.controller;

import com.pro_servises.pro.config.JwtAuth;
import com.pro_servises.pro.dto.LoginRequest;
import com.pro_servises.pro.dto.SignupRequest;
import com.pro_servises.pro.enums.Role;
import com.pro_servises.pro.model.Person;
import com.pro_servises.pro.serviceImp.PersonServiceImp;
import com.pro_servises.pro.serviceImp.PersonServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PersonController {

    private final PersonServiceImp personServiceImp;
    private final AuthenticationManager authenticationManager;

    public PersonController(AuthenticationManager authenticationManager, PersonServiceImp personServiceImp) {
        this.authenticationManager = authenticationManager;
        this.personServiceImp = personServiceImp;
    }

    /**
     * Sign up a new user
     *
     * @param role the role of the user (e.g., ADMIN, USER)
     * @param signUpRequest the sign-up request containing user details
     * @return the created Person object
     */
    @PostMapping("/signup")
    public ResponseEntity<Person> signUp(@RequestParam("role") Role role, @RequestBody SignupRequest signUpRequest) {
        Person createPerson = personServiceImp.signUp(role, signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPerson);
    }

    /**
     * Sign in an existing user
     *
     * @param loginRequest the login request containing username and password
     * @return a ResponseEntity containing a JWT token
     */
    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Person person = personServiceImp.findByUserName(loginRequest.getUsername());

        String token = JwtAuth.generateToken(person.getUsername(), person.getRoles(), person.getId());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
