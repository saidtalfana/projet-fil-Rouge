package com.pro_servises.pro.controller;

import com.pro_servises.pro.dto.ContactDto;
import com.pro_servises.pro.serviceImp.ContactServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private final ContactServiceImpl contactServiceImpl;

    public ContactController(ContactServiceImpl contactServiceImpl) {
        this.contactServiceImpl = contactServiceImpl;
    }

    /**
     * Add a new contact
     *
     * @param contactDto the contact data to be added
     * @return the created ContactDto object
     */
    @PostMapping("/add_contact")
    public ContactDto addContact(@RequestBody ContactDto contactDto) {
        return contactServiceImpl.addContact(contactDto);
    }

    /**
     * Get a list of all contacts
     *
     * @return a list of ContactDto objects
     */
    @GetMapping("/get_all_contact")
    public List<ContactDto> getAllContact() {
        return contactServiceImpl.getAllContacts();
    }
}
