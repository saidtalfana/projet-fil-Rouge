package com.pro_servises.pro;

import com.pro_servises.pro.dto.ContactDto;
import com.pro_servises.pro.mapper.ContactMapper;
import com.pro_servises.pro.model.Contact;
import com.pro_servises.pro.repository.ContactRepository;
import com.pro_servises.pro.serviceImp.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContactServiceImplTest {

    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactMapper contactMapper;

    private ContactDto contactDto;
    private Contact contact;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        contactDto = ContactDto.builder()
                .contactId(1)
                .name("Talfana") // Updated name
                .email("talfana@example.com")
                .phone("1234567890")
                .message("Hello!")
                .build();

        contact = new Contact();
        contact.setContactId(1);
        contact.setName("Talfana"); // Updated name
        contact.setEmail("talfana@example.com");
        contact.setPhone("1234567890");
        contact.setMessage("Hello!");
        contact.setDate(new Date(System.currentTimeMillis()));
        contact.setTime(new Time(System.currentTimeMillis()));
    }

    @Test
    void addContact_shouldSaveContactAndReturnDto() {
        when(contactMapper.mapToContact(contactDto)).thenReturn(contact);
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);
        when(contactMapper.mapToContactDto(any(Contact.class))).thenReturn(contactDto);

        ContactDto result = contactService.addContact(contactDto);

        assertNotNull(result);
        assertEquals(contactDto.getContactId(), result.getContactId());
        verify(contactRepository, times(1)).save(any(Contact.class));
    }

    @Test
    void getAllContacts_shouldReturnListOfContactDtos() {
        when(contactRepository.findAll()).thenReturn(Arrays.asList(contact));
        when(contactMapper.mapToContactDto(contact)).thenReturn(contactDto);

        List<ContactDto> result = contactService.getAllContacts();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(contactDto.getContactId(), result.get(0).getContactId());
        assertEquals("Talfana", result.get(0).getName()); // Check the name
    }
}
