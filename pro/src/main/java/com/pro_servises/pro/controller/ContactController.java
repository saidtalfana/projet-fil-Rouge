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

    @PostMapping("/add_contact")
    ContactDto addContact(@RequestBody ContactDto contactDto) {
        return contactServiceImpl.addContact(contactDto);
    }

    @GetMapping("/get_all_contact")
    List<ContactDto> getAllContact() {
        return contactServiceImpl.getAllContacts();
    }
}
