//package com.pro_servises.pro;
//
//import com.pro_servises.pro.model.Person;
//import com.pro_servises.pro.repository.PersonRepository;
//import com.pro_servises.pro.serviceImp.UserDetailsServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class UserDetailsServiceImplTest {
//
//    @Mock
//    private PersonRepository personRepository;
//
//    @InjectMocks
//    private UserDetailsServiceImpl userDetailsServiceImpl;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testLoadUserByUsername_Success() {
//        // Given
//        String username = "testUser";
//        Person person = new Person();
//        person.setUsername(username);
//
//        when(personRepository.findByUsername(username)).thenReturn(person);
//
//        // When
//        UserDetails result = userDetailsServiceImpl.loadUserByUsername(username);
//
//        // Then
//        assertNotNull(result);
//        assertEquals(username, result.getUsername());
//        verify(personRepository, times(1)).findByUsername(username);
//    }
//
//    @Test
//    void testLoadUserByUsername_UserNotFound() {
//        // Given
//        String username = "unknownUser";
//        when(personRepository.findByUsername(username)).thenReturn(null);
//
//        // When / Then
//        assertThrows(UsernameNotFoundException.class, () -> userDetailsServiceImpl.loadUserByUsername(username));
//        verify(personRepository, times(1)).findByUsername(username);
//    }
//}
