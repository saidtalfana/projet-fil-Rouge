package com.pro_servises.pro.controller;


import com.pro_servises.pro.config.JwtAuth;
import com.pro_servises.pro.dto.LoginRequest;
import com.pro_servises.pro.dto.SignupRequest;
import com.pro_servises.pro.enums.Role;
import com.pro_servises.pro.model.Person;
import com.pro_servises.pro.serviceImp.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private PersonService personService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/salam")
   public String one(){
        return "assalam ailaikum";
    }

    @PostMapping("/signup")
    public ResponseEntity<Person> signUp(@RequestParam("role") Role role, @RequestBody SignupRequest signUpRequest) {
        Person createPerson = personService.signUp(role, signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPerson);
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Person person = personService.findByUserName(loginRequest.getUsername());

        String token = JwtAuth.generateToken(person.getUsername(), person.getRoles(),person.getId());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
