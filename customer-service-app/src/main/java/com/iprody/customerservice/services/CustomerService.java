package com.iprody.customerservice.services;

import com.iprody.customerservice.dto.customer.CustomerDto;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerDto save(CustomerDto customer);

    Optional<CustomerDto> findById(Long id);

    void updateCustomerName(Long id, CustomerDto customerDto);

    void updateCustomerSurname(Long id, CustomerDto customerDto);

    void updateCustomerEmail(Long id, CustomerDto customerDto);

    void updateCustomerTelegramId(Long id, CustomerDto customerDto);

    void updateCustomerCountry(Long id, CustomerDto customerDto);

    Page<CustomerDto> findAll(
            String name,
            String surname,
            String countryName,
            String sortDirection,
            Pageable pageable
    );
}
