package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.model.Person;
import com.pro_servises.pro.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link UserDetailsService} interface for loading user-specific data.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonRepository personRepository;

    /**
     * Constructs a {@link UserDetailsServiceImpl} instance.
     *
     * @param personRepository the repository for managing persons
     */
    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Loads a user by their username.
     *
     * @param username the username of the user to be loaded
     * @return the user details of the person
     * @throws UsernameNotFoundException if no user is found with the given username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("person not found");
        }
        return person;
    }
}
