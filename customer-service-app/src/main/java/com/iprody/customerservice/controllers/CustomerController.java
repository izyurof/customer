package com.iprody.customerservice.controllers;

import com.iprody.customerservice.dto.CustomerDto;
import com.iprody.customerservice.services.CustomerService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(
            @Valid @RequestBody CustomerDto customerDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(
                    bindingResult.getAllErrors(),
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                customerService.save(customerDto),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Long id) {
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

    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAllCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String countryName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size,
            @RequestParam(required = false) String sortDirection
    ) {
        Pageable pageable = getPageable(page, size, sortDirection);

        Page<CustomerDto> allCustomer = customerService
                .findAll(name, surname, countryName, sortDirection, pageable);
        return new ResponseEntity<>(
                allCustomer.getContent(),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}/update/name")
    public ResponseEntity<?> updateCustomerName(
            @PathVariable("id") Long id,
            @Valid @RequestBody CustomerDto customerDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        customerService.updateCustomerName(id, customerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/update/surname")
    public ResponseEntity<?> updateCustomerSurname(
            @PathVariable("id") Long id,
            @Valid @RequestBody CustomerDto customerDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        customerService.updateCustomerSurname(id, customerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/update/email")
    public ResponseEntity<?> updateCustomerEmail(
            @PathVariable("id") Long id,
            @Valid @RequestBody CustomerDto customerDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        customerService.updateCustomerEmail(id, customerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/update/telegram_id")
    public ResponseEntity<?> updateCustomerTelegramId(
            @PathVariable("id") Long id,
            @Valid @RequestBody CustomerDto customerDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        customerService.updateCustomerTelegramId(id, customerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/update/country")
    public ResponseEntity<?> updateCustomerCountry(
            @PathVariable("id") Long id,
            @Valid @RequestBody CustomerDto customerDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
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
