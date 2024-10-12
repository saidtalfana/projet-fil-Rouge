package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.SignupRequest;
import com.pro_servises.pro.enums.Role;
import com.pro_servises.pro.model.Admin;
import com.pro_servises.pro.model.Person;
import com.pro_servises.pro.model.Provider;
import com.pro_servises.pro.model.User;
import com.pro_servises.pro.repository.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImp {

    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    public PersonServiceImp(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Person findByUserName(String username){

        return personRepository.findByUsername(username);
    }

    public Person signUp(Role role, SignupRequest signUpRequest) {
        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        if (role == null) {
            throw new IllegalArgumentException("Invalid person type");
        }
        Person person;
        switch (role) {

            case ADMIN:
                person = new Admin();
                person.setRoles(Role.ADMIN);
                break;
            case USER:
                person = new User();
                person.setRoles(Role.USER);
                break;
            case PROVIDER:
                person = new Provider()  ;
                person.setRoles(Role.PROVIDER);
                break;
            default:
                throw new IllegalArgumentException("Invalid person type");
        }

        person.setName(signUpRequest.getName());
        person.setUsername(signUpRequest.getUsername());
        person.setEmail(signUpRequest.getEmail());
        person.setPassword(hashedPassword);

        return personRepository.save(person);    }

}
