package com.pro_servises.pro;

import com.pro_servises.pro.model.Person;
import com.pro_servises.pro.repository.PersonRepository;
import com.pro_servises.pro.serviceImp.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_Success() {
        // Given
        String username = "testuser";
        Person person = new Person();
        person.setUsername(username);
        // Mock the repository method
        when(personRepository.findByUsername(username)).thenReturn(person);

        // When
        UserDetails result = userDetailsService.loadUserByUsername(username);

        // Then
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(personRepository).findByUsername(username);
    }

    @Test
    void testLoadUserByUsername_NotFound() {
        // Given
        String username = "unknownuser";
        when(personRepository.findByUsername(username)).thenReturn(null);

        // When/Then
        UsernameNotFoundException thrown = assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(username)
        );
        assertEquals("person not found", thrown.getMessage());
        verify(personRepository).findByUsername(username);
    }
}
