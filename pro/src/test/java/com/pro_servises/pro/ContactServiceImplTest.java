package com.pro_servises.pro;

import com.pro_servises.pro.dto.ContactDto;
import com.pro_servises.pro.mapper.ContactMapper;
import com.pro_servises.pro.model.Contact;
import com.pro_servises.pro.repository.ContactRepository;
import com.pro_servises.pro.service.ContactService;
import com.pro_servises.pro.serviceImp.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ContactServiceImplTest {

    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactMapper contactMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddContact() {
        // Given
        ContactDto contactDto = new ContactDto();
        Contact contact = new Contact();
        Contact savedContact = new Contact();
        savedContact.setContactId(1);  // Assurez-vous que votre Contact a une méthode pour définir son ID
        ContactDto savedContactDto = new ContactDto();

        when(contactMapper.mapToContact(contactDto)).thenReturn(contact);
        when(contactRepository.save(contact)).thenReturn(savedContact);
        when(contactMapper.mapToContactDto(savedContact)).thenReturn(savedContactDto);

        // When
        ContactDto result = contactService.addContact(contactDto);

        // Then
        assertNotNull(result);
        assertEquals(savedContactDto, result);
        verify(contactRepository, times(1)).save(contact);
        verify(contactMapper, times(1)).mapToContact(contactDto);
        verify(contactMapper, times(1)).mapToContactDto(savedContact);
    }

    @Test
    void testGetAllContacts() {
        // Given
        Contact contact = new Contact();
        ContactDto contactDto = new ContactDto();
        List<Contact> contacts = Collections.singletonList(contact);
        List<ContactDto> contactDtos = Collections.singletonList(contactDto);

        when(contactRepository.findAll()).thenReturn(contacts);
        when(contactMapper.mapToContactDto(contact)).thenReturn(contactDto);

        // When
        List<ContactDto> result = contactService.getAllContacts();

        // Then
        assertNotNull(result);
        assertEquals(contactDtos.size(), result.size());
        assertEquals(contactDtos.get(0), result.get(0));
        verify(contactRepository, times(1)).findAll();
        verify(contactMapper, times(1)).mapToContactDto(contact);
    }
}
