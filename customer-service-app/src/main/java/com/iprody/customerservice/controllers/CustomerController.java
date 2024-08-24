package com.iprody.customerservice.controllers;

import com.iprody.customerservice.dto.customer.CustomerDto;
import com.iprody.customerservice.services.CustomerService;
import java.util.List;
import org.openapitools.api.V1CustomerApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CustomerController implements V1CustomerApi {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<CustomerDto> createCustomer(CustomerDto customerDto) {
        return new ResponseEntity<>(
                customerService.save(customerDto),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<CustomerDto> getCustomer(Long id) {
        CustomerDto customerDto = customerService
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Customer with id - %s not found", id)
                        )
                );
        return new ResponseEntity<>(
                customerDto,
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<List<CustomerDto>> getAllCustomers(
            String name,
            String surname,
            String countryName,
            Integer page,
            Integer size,
            String sortDirection
    ) {
        Pageable pageable = getPageable(page, size, sortDirection);

        Page<CustomerDto> allCustomer = customerService
                .findAll(name, surname, countryName, sortDirection, pageable);
        return new ResponseEntity<>(
                allCustomer.getContent(),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Void> updateCustomerName(Long id, CustomerDto customerDto) {
        customerService.updateCustomerName(id, customerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> updateCustomerSurname(Long id, CustomerDto customerDto) {
        customerService.updateCustomerSurname(id, customerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> updateCustomerEmail(Long id, CustomerDto customerDto) {
        customerService.updateCustomerEmail(id, customerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> updateCustomerTelegramId(Long id, CustomerDto customerDto) {
        customerService.updateCustomerTelegramId(id, customerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> updateCustomerCountry(Long id, CustomerDto customerDto) {
        customerService.updateCustomerCountry(id, customerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private static Pageable getPageable(int page, int size, String sortDirection) {
        Pageable pageable;
        if (sortDirection == null || sortDirection.isEmpty()) {
            pageable = PageRequest.of(page, size);
        } else {
            Sort sort =
                    sortDirection.equalsIgnoreCase("asc")
                            ? Sort.by("country.name").ascending()
                            : Sort.by("country.name").descending();

            pageable = PageRequest.of(page, size, sort);
        }
        return pageable;
    }
}
