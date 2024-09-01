package com.iprody.customerservice.services;

import static com.iprody.customerservice.utils.builder.ContactDetailsDtoBuilder.getContactDetailsDto;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
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
    }

    @Test
    public void shouldReturnSavedCustomer() {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto(getCustomerDto(), 0);

        // when
        CustomerDto customerDtoFromDb = customerService.save(customerDto);

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
                .isEqualTo(customerDtoFromDb.getName());
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
        CustomerDto customerDto = getFullFieldCustomerDto(getCustomerDto(), 0);
        CustomerDto savedCustomerDto = customerService.save(customerDto);

        // when
        customerDto.setName("Maude");
        customerService.updateCustomerName(savedCustomerDto.getId(), customerDto);
        CustomerDto changedCustomerDto = customerService
                .findById(savedCustomerDto.getId())
                .orElseThrow(AssertionError::new);

        // then
        assertThat(savedCustomerDto.getId()).isEqualTo(changedCustomerDto.getId());
        assertThat(changedCustomerDto.getName()).isEqualTo("Maude");
        assertThat(savedCustomerDto.getName()).isNotEqualTo(changedCustomerDto.getName());
    }

    @Test
    public void shouldUpdateCustomerSurnameIfCustomerIsExist() {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto(getCustomerDto(), 0);
        CustomerDto savedCustomerDto = customerService.save(customerDto);

        // when
        customerDto.setSurname("Kasparov");
        customerService.updateCustomerSurname(savedCustomerDto.getId(), customerDto);
        CustomerDto changedCustomerDto = customerService
                .findById(savedCustomerDto.getId())
                .orElseThrow(AssertionError::new);

        // then
        assertThat(savedCustomerDto.getId()).isEqualTo(changedCustomerDto.getId());
        assertThat(changedCustomerDto.getSurname()).isEqualTo("Kasparov");
        assertThat(savedCustomerDto.getSurname()).isNotEqualTo(changedCustomerDto.getSurname());
    }

    @Test
    public void shouldUpdateCustomerEmailIfCustomerIsExist() {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto(getCustomerDto(), 0);
        CustomerDto savedCustomerDto = customerService.save(customerDto);

        // when
        customerDto.getContactDetailsDto().setEmail("newleboski@gmail.com");
        customerService.updateCustomerEmail(savedCustomerDto.getId(), customerDto);
        CustomerDto changedCustomerDto = customerService
                .findById(savedCustomerDto.getId())
                .orElseThrow(AssertionError::new);

        // then
        assertThat(savedCustomerDto.getId()).isEqualTo(changedCustomerDto.getId());
        assertThat(changedCustomerDto.getContactDetailsDto().getEmail())
                .isEqualTo("newleboski@gmail.com");
        assertThat(savedCustomerDto.getContactDetailsDto().getEmail())
                .isNotEqualTo(changedCustomerDto.getContactDetailsDto().getEmail());
    }

    @Test
    public void shouldUpdateCustomerTelegramIdIfCustomerIfExist() {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto(getCustomerDto(), 0);
        CustomerDto savedCustomerDto = customerService.save(customerDto);

        // when
        customerDto.getContactDetailsDto().setTelegramId("@newlebowski");
        customerService.updateCustomerTelegramId(savedCustomerDto.getId(), customerDto);
        CustomerDto changedCustomerDto = customerService
                .findById(savedCustomerDto.getId())
                .orElseThrow(AssertionError::new);

        // then
        assertThat(savedCustomerDto.getId()).isEqualTo(changedCustomerDto.getId());
        assertThat(changedCustomerDto.getContactDetailsDto().getTelegramId())
                .isEqualTo("@newlebowski");
        assertThat(changedCustomerDto.getContactDetailsDto().getTelegramId())
                .isNotEqualTo(savedCustomerDto.getContactDetailsDto().getTelegramId());
    }

    @Test
    public void shouldUpdateCustomerCountry() {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto(getCustomerDto(), 0);
        CustomerDto savedCustomerDto = customerService.save(customerDto);

        // when
        CustomerDto customerDtoWithNewCountry = getFullFieldCustomerDto(getCustomerDto(), 1);
        customerService.updateCustomerCountry(savedCustomerDto.getId(), customerDtoWithNewCountry);
        CustomerDto changedCustomerDto = customerService
                .findById(savedCustomerDto.getId())
                .orElseThrow(AssertionError::new);

        // then
        assertThat(changedCustomerDto.getCountryDto().getCountryCode())
                .isEqualTo(customerDtoWithNewCountry.getCountryDto().getCountryCode())
                .isNotEqualTo(savedCustomerDto.getCountryDto().getCountryCode())
                .isNotNull();
        assertThat(changedCustomerDto.getCountryDto().getName())
                .isEqualTo(customerDtoWithNewCountry.getCountryDto().getName())
                .isNotEqualTo(savedCustomerDto.getCountryDto().getName())
                .isNotNull();
    }

    @Test
    public void shouldReturnFirstPageWithTwoCustomersWhenTotalCustomersMoreThenTwo() {
        // given
        PageRequest pageable = PageRequest.of(0, 2);

        CustomerDto firstCustomerDto = getFullFieldCustomerDto(getCustomerDto(), 0);
        customerService.save(firstCustomerDto);

        CustomerDto secondCustomerDto = getFullFieldCustomerDto(getSecondCustomerDto(), 1);
        customerService.save(secondCustomerDto);

        CustomerDto thirdCustomerDto = getFullFieldCustomerDto(getThirdCustomerDto(), 2);
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

        CustomerDto firstCustomerDto = getFullFieldCustomerDto(getCustomerDto(), 0);
        customerService.save(firstCustomerDto);

        CustomerDto secondCustomerDto = getFullFieldCustomerDto(getSecondCustomerDto(), 1);
        customerService.save(secondCustomerDto);

        CustomerDto thirdCustomerDto = getFullFieldCustomerDto(getThirdCustomerDto(), 2);
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

        CustomerDto firstCustomerDto = getFullFieldCustomerDto(getCustomerDto(), 0);
        customerService.save(firstCustomerDto);

        CustomerDto secondCustomerDto = getFullFieldCustomerDto(getSecondCustomerDto(), 1);
        CustomerDto secondSavedCustomerDto = customerService.save(secondCustomerDto);

        CustomerDto thirdCustomerDto = getFullFieldCustomerDto(getThirdCustomerDto(), 2);
        CustomerDto thirdSavedCustomerDto = customerService.save(thirdCustomerDto);

        // then
        Page<CustomerDto> result = customerService.findAll(
                firstCustomerDto.getName(),
                null,
                null,
                null,
                pageable
        );

        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getContent().get(0).getName())
                .isEqualTo(result.getContent().get(1).getName())
                .isEqualTo(firstCustomerDto.getName())
                .isEqualTo(secondSavedCustomerDto.getName())
                .isNotEqualTo(thirdSavedCustomerDto.getName());

    }

    @Test
    public void shouldReturnTheCustomersWithSpecifiedSurnameOnlyWithoutOrdering() {
        // given
        PageRequest pageable = PageRequest.of(0, 10);

        CustomerDto firstCustomerDto = getFullFieldCustomerDto(getCustomerDto(), 0);
        customerService.save(firstCustomerDto);

        CustomerDto secondCustomerDto = getFullFieldCustomerDto(getSecondCustomerDto(), 1);
        CustomerDto secondSavedCustomerDto = customerService.save(secondCustomerDto);

        CustomerDto thirdCustomerDto = getFullFieldCustomerDto(getThirdCustomerDto(), 2);
        customerService.save(thirdCustomerDto);

        // when
        Page<CustomerDto> result = customerService.findAll(
                null,
                secondSavedCustomerDto.getSurname(),
                null,
                null,
                pageable
        );

        // then
        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getContent().get(0).getSurname())
                .isEqualTo(result.getContent().get(1).getSurname())
                .isEqualTo(thirdCustomerDto.getSurname())
                .isEqualTo(secondSavedCustomerDto.getSurname());
    }

    @Test
    public void shouldReturnTheCustomersWithSpecifiedCountryNameOnlyWithoutOrdering() {
        // given
        PageRequest pageable = PageRequest.of(0, 10);

        CustomerDto firstCustomerDto = getFullFieldCustomerDto(getCustomerDto(), 0);
        customerService.save(firstCustomerDto);

        CustomerDto secondCustomerDto = getFullFieldCustomerDto(getSecondCustomerDto(), 1);
        customerService.save(secondCustomerDto);

        CustomerDto thirdCustomerDto = getFullFieldCustomerDto(getThirdCustomerDto(), 2);
        customerService.save(thirdCustomerDto);

        // when
        Page<CustomerDto> result = customerService.findAll(
                null,
                null,
                secondCustomerDto.getCountryDto().getName(),
                null,
                pageable
        );

        // then
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getCountryDto().getName())
                .isEqualTo(secondCustomerDto.getCountryDto().getName());
    }

    @Test
    public void shouldReturnSortedPageWithAscSortDirection() {
        // given
        PageRequest pageable = PageRequest.of(0, 10);

        CustomerDto firstCustomerDto = getFullFieldCustomerDto(getCustomerDto(), 0);
        customerService.save(firstCustomerDto);

        CustomerDto secondCustomerDto = getFullFieldCustomerDto(getSecondCustomerDto(), 1);
        customerService.save(secondCustomerDto);

        CustomerDto thirdCustomerDto = getFullFieldCustomerDto(getThirdCustomerDto(), 2);
        customerService.save(thirdCustomerDto);

        List<String> sortedCountryName = getSortedCountryName(
                firstCustomerDto,
                secondCustomerDto,
                thirdCustomerDto
        );

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
                .isEqualTo(sortedCountryName.get(0));
        assertThat(result.getContent().get(1).getCountryDto().getName())
                .isEqualTo(sortedCountryName.get(1));
        assertThat(result.getContent().get(2).getCountryDto().getName())
                .isEqualTo(sortedCountryName.get(2));
    }

    @Test
    public void shouldReturnSortedPageWithDescSortDirection() {
        // given
        PageRequest pageable = PageRequest.of(0, 10);
        CustomerDto firstCustomerDto = getFullFieldCustomerDto(getCustomerDto(), 0);
        customerService.save(firstCustomerDto);

        CustomerDto secondCustomerDto = getFullFieldCustomerDto(getSecondCustomerDto(), 1);
        customerService.save(secondCustomerDto);

        CustomerDto thirdCustomerDto = getFullFieldCustomerDto(getThirdCustomerDto(), 2);
        customerService.save(thirdCustomerDto);

        List<String> sortedCountryName = getSortedCountryName(
                firstCustomerDto,
                secondCustomerDto,
                thirdCustomerDto
        );

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
                .isEqualTo(sortedCountryName.get(2));
        assertThat(result.getContent().get(1).getCountryDto().getName())
                .isEqualTo(sortedCountryName.get(1));
        assertThat(result.getContent().get(2).getCountryDto().getName())
                .isEqualTo(sortedCountryName.get(0));
    }

    private CustomerDto getFullFieldCustomerDto(CustomerDto customerDto, int indexList) {
        customerDto.setContactDetailsDto(getContactDetailsDto());
        CountryDto countryDto = countryMapper
                .toCountryDto(countryRepository.findAll().get(indexList));
        customerDto.setCountryDto(countryDto);
        return customerDto;
    }

    private static List<String> getSortedCountryName(
            CustomerDto firstCustomerDto,
            CustomerDto secondCustomerDto,
            CustomerDto thirdCustomerDto
    ) {
        return Stream.of(
                        firstCustomerDto.getCountryDto().getName(),
                        secondCustomerDto.getCountryDto().getName(),
                        thirdCustomerDto.getCountryDto().getName()
                )
                .sorted()
                .toList();
    }
}
