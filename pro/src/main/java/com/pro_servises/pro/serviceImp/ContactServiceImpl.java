package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.ContactDto;
import com.pro_servises.pro.mapper.ContactMapper;
import com.pro_servises.pro.model.Contact;
import com.pro_servises.pro.repository.ContactRepository;
import com.pro_servises.pro.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactMapper contactMapper;

    @Override
    public ContactDto addContact(ContactDto contactDto) {
        Contact contact = contactMapper.mapToContact(contactDto);
        contact.setDate(new Date( System.currentTimeMillis()));
        contact.setTime(new Time( System.currentTimeMillis()));
        Contact savedContact = contactRepository.save(contact);
        return contactMapper.mapToContactDto(savedContact);
    }



    @Override
    public List<ContactDto> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream()
                .map(contactMapper::mapToContactDto)
                .collect(Collectors.toList());
    }

}
