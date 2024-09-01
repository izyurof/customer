package com.iprody.customerservice.utils.builder;

import com.iprody.customerservice.dto.contactdetails.ContactDetailsDto;
import com.iprody.customerservice.dto.country.CountryDto;
import com.iprody.customerservice.dto.customer.CustomerDto;
import java.time.Instant;

public class CustomerDtoBuilder {

    public static CustomerDto getCustomerDto() {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Jeffrey");
        customerDto.setSurname("Lebowski");
        return customerDto;
    }

    public static CustomerDto getSecondCustomerDto() {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Jeffrey");
        customerDto.setSurname("Stepanov");
        return customerDto;
    }

    public static CustomerDto getThirdCustomerDto() {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Petr");
        customerDto.setSurname("Stepanov");
        return customerDto;

    }

    public static CustomerDto getCustomerDtoWithId(Long id) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(id);
        customerDto.setName("Jeffrey");
        customerDto.setSurname("Lebowski");
        return customerDto;
    }

}
