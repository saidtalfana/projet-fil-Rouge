package com.pro_servises.pro.mapper;

import com.pro_servises.pro.dto.ContactDto;
import com.pro_servises.pro.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper( ContactMapper.class );

    ContactDto mapToContactDto(Contact contact);
    Contact mapToContact(ContactDto contactDto);

}
