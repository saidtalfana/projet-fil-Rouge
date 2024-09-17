package com.pro_servises.pro.controller;

import com.pro_servises.pro.dto.ContactDto;
import com.pro_servises.pro.service.ContactService;
import com.pro_servises.pro.serviceImp.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    private ContactServiceImpl contactServiceImpl;

    @PostMapping("/add_contact")
    ContactDto addContact(@RequestBody ContactDto contactDto) {
        return contactServiceImpl.addContact(contactDto);
    }

    @GetMapping("/get_all_contact")
    List<ContactDto> getAllContact() {
        return contactServiceImpl.getAllContacts();
    }
}
