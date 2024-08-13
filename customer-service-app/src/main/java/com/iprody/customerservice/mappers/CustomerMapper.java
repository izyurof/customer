package com.iprody.customerservice.mappers;

import com.iprody.customerservice.dto.CustomerDto;
import com.iprody.customerservice.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CountryMapper.class, ContactDetailsMapper.class}
)
public interface CustomerMapper {

    @Mappings({
        @Mapping(source = "country", target = "countryDto"),
        @Mapping(source = "contactDetails", target = "contactDetailsDto"),
        @Mapping(source = "createdAt", target = "createdAt"),
        @Mapping(source = "updatedAt", target = "updatedAt")
    })
    CustomerDto toCustomerDto(Customer customer);

    @Mappings({
        @Mapping(source = "countryDto", target = "country"),
        @Mapping(source = "contactDetailsDto", target = "contactDetails"),
        @Mapping(source = "createdAt", target = "createdAt"),
        @Mapping(source = "updatedAt", target = "updatedAt")
    })
    Customer toCustomer(CustomerDto customerDto);

}
