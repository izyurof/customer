package com.iprody.customerservice.mappers;

import com.iprody.customerservice.dto.contactdetails.ContactDetailsDto;
import com.iprody.customerservice.models.ContactDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactDetailsMapper {

    @Mappings({
        @Mapping(source = "createdAt", target = "createdAt"),
        @Mapping(source = "updatedAt", target = "updatedAt")
    })
    ContactDetailsDto toContactDetailsDto(ContactDetails contactDetails);

    @Mappings({
        @Mapping(source = "createdAt", target = "createdAt"),
        @Mapping(source = "updatedAt", target = "updatedAt")
    })
    ContactDetails toContactDetails(ContactDetailsDto contactDetailsDto);
}
