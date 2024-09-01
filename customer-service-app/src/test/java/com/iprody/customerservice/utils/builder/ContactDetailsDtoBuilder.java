package com.iprody.customerservice.utils.builder;

import com.iprody.customerservice.dto.contactdetails.ContactDetailsDto;

public class ContactDetailsDtoBuilder {

    public static ContactDetailsDto getContactDetailsDto() {
        ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
        contactDetailsDto.setEmail("lebowski@gmail.com");
        contactDetailsDto.setTelegramId("@lebowski");
        return contactDetailsDto;
    }


    public static ContactDetailsDto getContactDetailsDtoWithId(Long id) {
        ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
        contactDetailsDto.setId(id);
        contactDetailsDto.setEmail("lebowski@gmail.com");
        contactDetailsDto.setTelegramId("@lebowski");
        return contactDetailsDto;
    }

}
