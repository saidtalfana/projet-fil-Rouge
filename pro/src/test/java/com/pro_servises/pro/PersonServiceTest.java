package com.pro_servises.pro;

import com.pro_servises.pro.dto.SignupRequest;
import com.pro_servises.pro.enums.Role;
import com.pro_servises.pro.model.Admin;
import com.pro_servises.pro.model.Person;
import com.pro_servises.pro.model.Provider;
import com.pro_servises.pro.model.User;
import com.pro_servises.pro.repository.PersonRepository;
import com.pro_servises.pro.serviceImp.PersonServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PersonServiceTest {

    @InjectMocks
    private PersonServiceImp personServiceImp;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByUserName() {
        // Given
        String username = "testUser";
        Person person = new User(); // ou Admin ou Provider selon le test
        when(personRepository.findByUsername(username)).thenReturn(person);

        // When
        Person result = personServiceImp.findByUserName(username);

        // Then
        assertNotNull(result);
        assertEquals(person, result);
        verify(personRepository, times(1)).findByUsername(username);
    }

    @Test
    void testSignUpAsAdmin() {
        // Given
        SignupRequest signUpRequest = new SignupRequest();
        signUpRequest.setName("Admin Name");
        signUpRequest.setUsername("adminUsername");
        signUpRequest.setEmail("admin@example.com");
        signUpRequest.setPassword("password");

        Admin admin = new Admin();
        admin.setName("Admin Name");
        admin.setUsername("adminUsername");
        admin.setEmail("admin@example.com");
        admin.setPassword("hashedPassword"); // Assurez-vous que ceci correspond à ce qui est attendu

        when(passwordEncoder.encode(signUpRequest.getPassword())).thenReturn("hashedPassword");
        when(personRepository.save(any(Admin.class))).thenReturn(admin);

        // When
        Person result = personServiceImp.signUp(Role.ADMIN, signUpRequest);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof Admin);
        Admin resultAdmin = (Admin) result;
        assertEquals("Admin Name", resultAdmin.getName());
        assertEquals("adminUsername", resultAdmin.getUsername());
        assertEquals("admin@example.com", resultAdmin.getEmail());
        assertEquals("hashedPassword", resultAdmin.getPassword());
        verify(passwordEncoder, times(1)).encode(signUpRequest.getPassword());
        verify(personRepository, times(1)).save(any(Admin.class));
    }

    @Test
    void testSignUpAsUser() {
        // Given
        SignupRequest signUpRequest = new SignupRequest();
        signUpRequest.setName("User Name");
        signUpRequest.setUsername("userUsername");
        signUpRequest.setEmail("user@example.com");
        signUpRequest.setPassword("password");

        User user = new User();
        user.setName("User Name");
        user.setUsername("userUsername");
        user.setEmail("user@example.com");
        user.setPassword("hashedPassword");

        when(passwordEncoder.encode(signUpRequest.getPassword())).thenReturn("hashedPassword");
        when(personRepository.save(any(User.class))).thenReturn(user);

        // When
        Person result = personServiceImp.signUp(Role.USER, signUpRequest);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof User);
        User resultUser = (User) result;
        assertEquals("User Name", resultUser.getName());
        assertEquals("userUsername", resultUser.getUsername());
        assertEquals("user@example.com", resultUser.getEmail());
        assertEquals("hashedPassword", resultUser.getPassword());
        verify(passwordEncoder, times(1)).encode(signUpRequest.getPassword());
        verify(personRepository, times(1)).save(any(User.class));
    }

    @Test
    void testSignUpAsProvider() {
        // Given
        SignupRequest signUpRequest = new SignupRequest();
        signUpRequest.setName("Provider Name");
        signUpRequest.setUsername("providerUsername");
        signUpRequest.setEmail("provider@example.com");
        signUpRequest.setPassword("password");

        Provider provider = new Provider();
        provider.setName("Provider Name");
        provider.setUsername("providerUsername");
        provider.setEmail("provider@example.com");
        provider.setPassword("hashedPassword");

        when(passwordEncoder.encode(signUpRequest.getPassword())).thenReturn("hashedPassword");
        when(personRepository.save(any(Provider.class))).thenReturn(provider);

        // When
        Person result = personServiceImp.signUp(Role.PROVIDER, signUpRequest);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof Provider);
        Provider resultProvider = (Provider) result;
        assertEquals("Provider Name", resultProvider.getName());
        assertEquals("providerUsername", resultProvider.getUsername());
        assertEquals("provider@example.com", resultProvider.getEmail());
        assertEquals("hashedPassword", resultProvider.getPassword());
        verify(passwordEncoder, times(1)).encode(signUpRequest.getPassword());
        verify(personRepository, times(1)).save(any(Provider.class));
    }

    @Test
    void testSignUpWithInvalidRole() {
        // Given
        SignupRequest signUpRequest = new SignupRequest();
        signUpRequest.setName("Invalid Role Name");
        signUpRequest.setUsername("invalidRoleUsername");
        signUpRequest.setEmail("invalidRole@example.com");
        signUpRequest.setPassword("password");

        // When/Then
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> personServiceImp.signUp(null, signUpRequest)
        );
        assertEquals("Invalid person type", thrown.getMessage());
    }
}
