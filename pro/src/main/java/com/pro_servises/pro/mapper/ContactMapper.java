package com.pro_servises.pro.mapper;

import com.pro_servises.pro.dto.ContactDto;
import com.pro_servises.pro.model.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactDto mapToContactDto(Contact contact);
    Contact mapToContact(ContactDto contactDto);

}
