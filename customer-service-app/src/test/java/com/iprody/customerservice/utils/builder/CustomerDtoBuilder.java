package com.iprody.customerservice.utils.builder;

import com.iprody.customerservice.dto.ContactDetailsDto;
import com.iprody.customerservice.dto.CountryDto;
import com.iprody.customerservice.dto.CustomerDto;
import java.time.Instant;

public class CustomerDtoBuilder {

    public static CustomerDto getCustomerDto() {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Jeffrey");
        customerDto.setSurname("Lebowski");
        customerDto.setCreatedAt(Instant.now());
        customerDto.setUpdatedAt(Instant.now());

        CountryDto countryDto = new CountryDto();
        countryDto.setCountryCode("AIA");
        countryDto.setName("Anguilla");
        countryDto.setCreatedAt(Instant.now());
        countryDto.setUpdatedAt(Instant.now());
        customerDto.setCountryDto(countryDto);

        ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
        contactDetailsDto.setEmail("lebowski@gmail.com");
        contactDetailsDto.setTelegramId("@lebowski");
        contactDetailsDto.setCreatedAt(Instant.now());
        contactDetailsDto.setUpdatedAt(Instant.now());
        customerDto.setContactDetailsDto(contactDetailsDto);
        return customerDto;
    }

    public static CustomerDto getSecondCustomerDto() {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Jeffrey");
        customerDto.setSurname("Stepanov");
        customerDto.setCreatedAt(Instant.now());
        customerDto.setUpdatedAt(Instant.now());

        CountryDto countryDto = new CountryDto();
        countryDto.setCountryCode("BEN");
        countryDto.setName("Benin");
        countryDto.setCreatedAt(Instant.now());
        countryDto.setUpdatedAt(Instant.now());
        customerDto.setCountryDto(countryDto);

        ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
        contactDetailsDto.setEmail("lebowski@gmail.com");
        contactDetailsDto.setTelegramId("@lebowski");
        contactDetailsDto.setCreatedAt(Instant.now());
        contactDetailsDto.setUpdatedAt(Instant.now());
        customerDto.setContactDetailsDto(contactDetailsDto);
        return customerDto;
    }

    public static CustomerDto getThirdCustomerDto() {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Petr");
        customerDto.setSurname("Stepanov");
        customerDto.setCreatedAt(Instant.now());
        customerDto.setUpdatedAt(Instant.now());

        CountryDto countryDto = new CountryDto();
        countryDto.setCountryCode("BVT");
        countryDto.setName("Bouvet Island");
        countryDto.setCreatedAt(Instant.now());
        countryDto.setUpdatedAt(Instant.now());
        customerDto.setCountryDto(countryDto);

        ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
        contactDetailsDto.setEmail("lebowski@gmail.com");
        contactDetailsDto.setTelegramId("@lebowski");
        contactDetailsDto.setCreatedAt(Instant.now());
        contactDetailsDto.setUpdatedAt(Instant.now());
        customerDto.setContactDetailsDto(contactDetailsDto);
        return customerDto;
    }

    public static CustomerDto getCustomerDtoWithId() {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setName("Jeffrey");
        customerDto.setSurname("Lebowski");
        customerDto.setCreatedAt(Instant.now());
        customerDto.setUpdatedAt(Instant.now());

        CountryDto countryDto = new CountryDto();
        countryDto.setId(1L);
        countryDto.setCountryCode("AIA");
        countryDto.setName("Anguilla");
        countryDto.setCreatedAt(Instant.now());
        countryDto.setUpdatedAt(Instant.now());
        customerDto.setCountryDto(countryDto);

        ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
        contactDetailsDto.setId(1L);
        contactDetailsDto.setEmail("lebowski@gmail.com");
        contactDetailsDto.setTelegramId("@lebowski");
        contactDetailsDto.setCreatedAt(Instant.now());
        contactDetailsDto.setUpdatedAt(Instant.now());
        customerDto.setContactDetailsDto(contactDetailsDto);
        return customerDto;
    }
}
