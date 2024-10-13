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

/**
 * Implementation of the PersonService interface for managing user-related operations.
 */
@Service
public class PersonServiceImp {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for PersonServiceImp.
     *
     * @param personRepository The repository for accessing person data.
     * @param passwordEncoder  The password encoder for hashing passwords.
     */
    public PersonServiceImp(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Finds a Person by username.
     *
     * @param username The username of the person to find.
     * @return The Person associated with the given username.
     */
    public Person findByUserName(String username) {
        return personRepository.findByUsername(username);
    }

    /**
     * Registers a new person based on the specified role and signup request.
     *
     * @param role          The role of the person (ADMIN, USER, PROVIDER).
     * @param signUpRequest The signup request containing the person's details.
     * @return The newly created Person object.
     * @throws IllegalArgumentException If the role is invalid.
     */
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
                person = new Provider();
                person.setRoles(Role.PROVIDER);
                break;
            default:
                throw new IllegalArgumentException("Invalid person type");
        }

        person.setName(signUpRequest.getName());
        person.setUsername(signUpRequest.getUsername());
        person.setEmail(signUpRequest.getEmail());
        person.setPassword(hashedPassword);

        return personRepository.save(person);
    }
}
