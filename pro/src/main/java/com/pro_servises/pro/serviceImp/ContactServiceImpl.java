package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.ContactDto;
import com.pro_servises.pro.mapper.ContactMapper;
import com.pro_servises.pro.model.Contact;
import com.pro_servises.pro.repository.ContactRepository;
import com.pro_servises.pro.service.ContactService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link ContactService} interface for managing contacts.
 */
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    /**
     * Constructs a {@link ContactServiceImpl} instance.
     *
     * @param contactRepository the repository for managing contacts
     * @param contactMapper     the mapper for converting between Contact and ContactDto
     */
    public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    /**
     * Adds a new contact.
     *
     * @param contactDto the data transfer object containing contact information
     * @return the added contact as a ContactDto
     */
    @Override
    public ContactDto addContact(ContactDto contactDto) {
        Contact contact = contactMapper.mapToContact(contactDto);
        contact.setDate(new Date(System.currentTimeMillis()));
        contact.setTime(new Time(System.currentTimeMillis()));
        Contact savedContact = contactRepository.save(contact);
        return contactMapper.mapToContactDto(savedContact);
    }

    /**
     * Retrieves all contacts.
     *
     * @return a list of all contacts as ContactDto
     */
    @Override
    public List<ContactDto> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream()
                .map(contactMapper::mapToContactDto)
                .collect(Collectors.toList());
    }
}
