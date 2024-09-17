package com.pro_servises.pro.service;

import com.pro_servises.pro.dto.ArticleDto;
import com.pro_servises.pro.dto.ContactDto;

import java.util.List;

public interface ContactService {

    ContactDto addContact(ContactDto contactDto);

        List<ContactDto> getAllContacts();

}
