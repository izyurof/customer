package com.iprody.customerservice.services;

import static com.iprody.customerservice.utils.builder.CustomerDtoBuilder.getCustomerDto;
import static com.iprody.customerservice.utils.builder.CustomerDtoBuilder.getSecondCustomerDto;
import static com.iprody.customerservice.utils.builder.CustomerDtoBuilder.getThirdCustomerDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.iprody.customerservice.dto.country.CountryDto;
import com.iprody.customerservice.dto.customer.CustomerDto;
import com.iprody.customerservice.mappers.CountryMapper;
import com.iprody.customerservice.repositories.CountryRepository;
import com.iprody.customerservice.repositories.CustomerRepository;
import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class CustomerServiceTests {

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;
    
    @BeforeEach
    void cleanDataBase() {
        customerRepository.deleteAll();
        countryRepository.deleteAll();
    }

    @Test
    public void shouldReturnSavedCustomer() {
        // given
        CustomerDto customerDto = getCustomerDto();
        CustomerDto savedCustomerDto = customerService.save(customerDto);

        // when
        CustomerDto customerDtoFromDb = customerService.findById(savedCustomerDto.getId()).get();

        // then
        assertThat(customerDtoFromDb).isNotNull();
        assertThat(customerDtoFromDb.getId()).isNotNull();
        assertThat(customerDtoFromDb.getContactDetailsDto().getId()).isNotNull();
        assertThat(customerDtoFromDb.getCountryDto().getId()).isNotNull();
        assertThat(customerDtoFromDb.getCreatedAt()).isNotNull();
        assertThat(customerDtoFromDb.getUpdatedAt()).isNotNull();
        assertThat(customerDtoFromDb.getContactDetailsDto().getCreatedAt()).isNotNull();
        assertThat(customerDtoFromDb.getContactDetailsDto().getUpdatedAt()).isNotNull();
        assertThat(customerDtoFromDb.getCountryDto().getCreatedAt()).isNotNull();
        assertThat(customerDtoFromDb.getCountryDto().getUpdatedAt()).isNotNull();
        assertThat(customerDto.getName())
                .isEqualTo(savedCustomerDto.getName());
        assertThat(customerDtoFromDb.getSurname())
                .isEqualTo(customerDto.getSurname());
        assertThat(customerDtoFromDb.getCountryDto().getCountryCode())
                .isEqualTo(customerDto.getCountryDto().getCountryCode());
        assertThat(customerDtoFromDb.getCountryDto().getName())
                .isEqualTo(customerDto.getCountryDto().getName());
        assertThat(customerDtoFromDb.getContactDetailsDto().getTelegramId())
                .isEqualTo(customerDto.getContactDetailsDto().getTelegramId());
        assertThat(customerDtoFromDb.getContactDetailsDto().getEmail())
                .isEqualTo(customerDto.getContactDetailsDto().getEmail());
    }

    @Test
    public void shouldThrowExceptionWhenTryToSaveCustomerDtoIsNull() {
        // given
        CustomerDto customerDto = null;

        // when-then
        assertThatThrownBy(() -> customerService.save(customerDto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldReturnOptionalEmptyWhenCustomerIsNotExist() {
        // given
        Long nonExistingCustomerId = 88005553535L;

        // when
        Optional<CustomerDto> customerDtoFromDb =
                customerService.findById(nonExistingCustomerId);

        // then
        assertThat(customerDtoFromDb).isNotPresent();
    }

    @Test
    public void shouldUpdateCustomerNameIfCustomerIsExist() {
        // given
        CustomerDto customerDto = getCustomerDto();
        CustomerDto savedCustomerDto = customerService.save(customerDto);

        // when
        customerDto.setName("Maude");
        customerService.updateCustomerName(savedCustomerDto.getId(), customerDto);
        CustomerDto changedCustomerDto = customerService
                .findById(savedCustomerDto.getId())
                .orElseThrow(AssertionError::new);

        // then
        assertThat(changedCustomerDto.getName()).isEqualTo("Maude");
        assertThat(savedCustomerDto.getName()).isNotEqualTo(changedCustomerDto.getName());
    }

    @Test
    public void shouldUpdateCustomerSurnameIfCustomerIsExist() {
        // given
        CustomerDto customerDto = getCustomerDto();
        CustomerDto savedCustomerDto = customerService.save(customerDto);

        // when
        customerDto.setSurname("Kasparov");
        customerService.updateCustomerSurname(savedCustomerDto.getId(), customerDto);
        CustomerDto changedCustomerDto = customerService
                .findById(savedCustomerDto.getId())
                .orElseThrow(AssertionError::new);

        // then
        assertThat(changedCustomerDto.getSurname()).isEqualTo("Kasparov");
        assertThat(savedCustomerDto.getSurname()).isNotEqualTo(changedCustomerDto.getSurname());
    }

    @Test
    public void shouldUpdateCustomerEmailIfCustomerIsExist() {
        // given
        CustomerDto customerDto = getCustomerDto();
        CustomerDto savedCustomerDto = customerService.save(customerDto);

        // when
        customerDto.getContactDetailsDto().setEmail("newleboski@gmail.com");
        customerService.updateCustomerEmail(savedCustomerDto.getId(), customerDto);
        CustomerDto changedCustomerDto = customerService
                .findById(savedCustomerDto.getId())
                .orElseThrow(AssertionError::new);

        // then
        assertThat(changedCustomerDto.getContactDetailsDto().getEmail())
                .isEqualTo("newleboski@gmail.com");
        assertThat(savedCustomerDto.getContactDetailsDto().getEmail())
                .isNotEqualTo(changedCustomerDto.getContactDetailsDto().getEmail());
    }

    @Test
    public void shouldUpdateCustomerTelegramIdIfCustomerIfExist() {
        // given
        CustomerDto customerDto = getCustomerDto();
        CustomerDto savedCustomerDto = customerService.save(customerDto);

        // when
        customerDto.getContactDetailsDto().setTelegramId("@newlebowski");
        customerService.updateCustomerTelegramId(savedCustomerDto.getId(), customerDto);
        CustomerDto changedCustomerDto = customerService
                .findById(savedCustomerDto.getId())
                .orElseThrow(AssertionError::new);

        // then
        assertThat(changedCustomerDto.getContactDetailsDto().getTelegramId())
                .isEqualTo("@newlebowski");
        assertThat(changedCustomerDto.getContactDetailsDto().getTelegramId())
                .isNotEqualTo(savedCustomerDto.getContactDetailsDto().getTelegramId());
    }

    @Test
    public void shouldUpdateCustomerCountry() {
        // given
        CustomerDto customerDto = getCustomerDto();
        CustomerDto savedCustomerDto = customerService.save(customerDto);

        // when
        CountryDto newCountryDto = new CountryDto();
        newCountryDto.setCountryCode("ATA");
        newCountryDto.setName("Antarctica");
        newCountryDto.setCreatedAt(Instant.now());
        newCountryDto.setUpdatedAt(Instant.now());
        customerDto.setCountryDto(newCountryDto);
        countryRepository.save(countryMapper.toCountry(newCountryDto));
        customerDto.setCountryDto(newCountryDto);
        customerService.updateCustomerCountry(savedCustomerDto.getId(), customerDto);
        CustomerDto changedCustomerDto = customerService
                .findById(savedCustomerDto.getId())
                .orElseThrow(AssertionError::new);

        // then
        assertThat(changedCustomerDto.getCountryDto()).isNotNull();
        assertThat(changedCustomerDto.getCountryDto().getId())
                .isNotNull();
        assertThat(changedCustomerDto.getCountryDto().getCountryCode())
                .isEqualTo("ATA")
                .isNotEqualTo(savedCustomerDto.getCountryDto().getCountryCode());
        assertThat(changedCustomerDto.getCountryDto().getName())
                .isEqualTo("Antarctica")
                .isNotEqualTo(savedCustomerDto.getCountryDto().getName());
    }

    @Test
    public void shouldReturnFirstPageWithTwoCustomersWhenTotalCustomersMoreThenTwo() {
        // given
        PageRequest pageable = PageRequest.of(0, 2);
        CustomerDto firstCustomerDto = getCustomerDto();
        CustomerDto secondCustomerDto = getSecondCustomerDto();
        CustomerDto thirdCustomerDto = getThirdCustomerDto();
        customerService.save(firstCustomerDto);
        customerService.save(secondCustomerDto);
        customerService.save(thirdCustomerDto);

        // when
        Page<CustomerDto> result = customerService.findAll(
                null,
                null,
                null,
                null,
                pageable
        );

        // then
        assertThat(result.getContent().size()).isEqualTo(2);
    }

    @Test
    public void shouldReturnSecondPageWithOneCustomerWhenThereAreOnlyThreeCustomers() {
        // given
        PageRequest pageable = PageRequest.of(1, 2);
        CustomerDto firstCustomerDto = getCustomerDto();
        CustomerDto secondCustomerDto = getSecondCustomerDto();
        CustomerDto thirdCustomerDto = getThirdCustomerDto();
        customerService.save(firstCustomerDto);
        customerService.save(secondCustomerDto);
        customerService.save(thirdCustomerDto);

        // when
        Page<CustomerDto> result = customerService.findAll(
                null,
                null,
                null,
                null,
                pageable
        );

        // then
        assertThat(result.getContent().size()).isEqualTo(1);
    }

    @Test
    public void shouldReturnTheCustomerWithSpecifiedNameOnlyWithoutOrdering() {
        // given
        PageRequest pageable = PageRequest.of(0, 10);
        CustomerDto firstCustomerDto = getCustomerDto();
        CustomerDto secondCustomerDto = getSecondCustomerDto();
        CustomerDto thirdCustomerDto = getThirdCustomerDto();
        customerService.save(firstCustomerDto);
        customerService.save(secondCustomerDto);
        customerService.save(thirdCustomerDto);

        // then
        Page<CustomerDto> result = customerService.findAll(
                "Jeffrey",
                null,
                null,
                null,
                pageable
        );

        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getContent().get(0).getName())
                .isEqualTo(result.getContent().get(1).getName())
                .isEqualTo("Jeffrey");

    }

    @Test
    public void shouldReturnTheCustomersWithSpecifiedSurnameOnlyWithoutOrdering() {
        // given
        PageRequest pageable = PageRequest.of(0, 10);
        CustomerDto firstCustomerDto = getCustomerDto();
        CustomerDto secondCustomerDto = getSecondCustomerDto();
        CustomerDto thirdCustomerDto = getThirdCustomerDto();
        customerService.save(firstCustomerDto);
        customerService.save(secondCustomerDto);
        customerService.save(thirdCustomerDto);

        // when
        Page<CustomerDto> result = customerService.findAll(
                null,
                "Stepanov",
                null,
                null,
                pageable
        );

        // then
        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getContent().get(0).getSurname())
                .isEqualTo(result.getContent().get(1).getSurname())
                .isEqualTo("Stepanov");
    }

    @Test
    public void shouldReturnTheCustomersWithSpecifiedCountryNameOnlyWithoutOrdering() {
        // given
        PageRequest pageable = PageRequest.of(0, 10);
        CustomerDto firstCustomerDto = getCustomerDto();
        CustomerDto secondCustomerDto = getSecondCustomerDto();
        CustomerDto thirdCustomerDto = getThirdCustomerDto();
        customerService.save(firstCustomerDto);
        customerService.save(secondCustomerDto);
        customerService.save(thirdCustomerDto);

        // when
        Page<CustomerDto> result = customerService.findAll(
                null,
                null,
                "Benin",
                null,
                pageable
        );

        // then
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getCountryDto().getName()).isEqualTo("Benin");
    }

    @Test
    public void shouldReturnSortedPageWithAscSortDirection() {
        // given
        PageRequest pageable = PageRequest.of(0, 10);
        CustomerDto firstCustomerDto = getCustomerDto();
        CustomerDto secondCustomerDto = getSecondCustomerDto();
        CustomerDto thirdCustomerDto = getThirdCustomerDto();
        customerService.save(firstCustomerDto);
        customerService.save(secondCustomerDto);
        customerService.save(thirdCustomerDto);

        // when
        Page<CustomerDto> result = customerService.findAll(
                null,
                null,
                null,
                "asc",
                pageable
        );

        // then
        assertThat(result.getContent().get(0).getCountryDto().getName())
                .isEqualTo("Anguilla");
        assertThat(result.getContent().get(1).getCountryDto().getName())
                .isEqualTo("Benin");
        assertThat(result.getContent().get(2).getCountryDto().getName())
                .isEqualTo("Bouvet Island");
    }

    @Test
    public void shouldReturnSortedPageWithDescSortDirection() {
        // given
        PageRequest pageable = PageRequest.of(0, 10);
        CustomerDto firstCustomerDto = getCustomerDto();
        CustomerDto secondCustomerDto = getSecondCustomerDto();
        CustomerDto thirdCustomerDto = getThirdCustomerDto();
        customerService.save(firstCustomerDto);
        customerService.save(secondCustomerDto);
        customerService.save(thirdCustomerDto);

        // when
        Page<CustomerDto> result = customerService.findAll(
                null,
                null,
                null,
                "desc",
                pageable
        );

        // then
        assertThat(result.getContent().get(0).getCountryDto().getName())
                .isEqualTo("Bouvet Island");
        assertThat(result.getContent().get(1).getCountryDto().getName())
                .isEqualTo("Benin");
        assertThat(result.getContent().get(2).getCountryDto().getName())
                .isEqualTo("Anguilla");
    }
}
