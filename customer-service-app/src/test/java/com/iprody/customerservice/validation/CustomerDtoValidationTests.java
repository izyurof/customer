package com.iprody.customerservice.validation;

import static com.iprody.customerservice.utils.builder.ContactDetailsDtoBuilder.getContactDetailsDtoWithId;
import static com.iprody.customerservice.utils.builder.CountryDtoBuilder.getCountryDtoWithId;
import static com.iprody.customerservice.utils.builder.CustomerDtoBuilder.getCustomerDtoWithId;
import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.assertj.core.api.Assertions.assertThat;

import com.iprody.customerservice.dto.customer.CustomerDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class CustomerDtoValidationTests {

    private Validator validator = buildDefaultValidatorFactory().getValidator();

    @Test
    public void shouldReturnNoErrorsWhenValidCustomerDto() {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto();

        // when
        Set<ConstraintViolation<CustomerDto>> validate =
                validator.validate(customerDto);

        // then
        assertThat(validate).isEmpty();
    }

    @Test
    public void shouldReturnErrorWhenNullCustomerDtoName() {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto();
        customerDto.setName(null);

        // when
        List<ConstraintViolation<CustomerDto>> violations = validator
                .validate(customerDto).stream().toList();

        // then
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations.get(0).getPropertyPath().toString()).isEqualTo("name");
    }

    @Test
    public void shouldReturnErrorWhenCustomerDtoNameLongerThan30Characters() {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto();
        customerDto.setName("dtgfyhgiykyjtgfyikggujtfhghykyhikhujgyh");

        // when
        List<ConstraintViolation<CustomerDto>> violations = validator
                .validate(customerDto).stream().toList();

        // then
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations.get(0).getPropertyPath().toString()).isEqualTo("name");
    }

    @Test
    public void shouldReturnErrorWhenNullCustomerDtoSurname() {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto();
        customerDto.setSurname(null);

        // when
        List<ConstraintViolation<CustomerDto>> violations = validator
                .validate(customerDto).stream().toList();

        // then
        assertThat(violations.size()).isEqualTo(1);
        violations.get(0).getPropertyPath().toString().equals("surname");
    }

    @Test
    public void shouldReturnErrorWhenCustomerDtoSurnameLongerThan30Characters() {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto();
        customerDto.setSurname("dtgfyhgiykyjtgfyikggujtfhghykyhikhujgyh");

        // when
        List<ConstraintViolation<CustomerDto>> violations = validator
                .validate(customerDto).stream().toList();

        // then
        assertThat(violations.size()).isEqualTo(1);
        violations.get(0).getPropertyPath().toString().equals("surname");
    }

    @Test
    public void shouldReturnErrorWhenNullCustomerDtoCountryDto() {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto();
        customerDto.setCountryDto(null);

        // when
        List<ConstraintViolation<CustomerDto>> violations = validator
                .validate(customerDto).stream().toList();

        // then
        assertThat(violations.size()).isEqualTo(1);
        violations.get(0).getPropertyPath().toString().equals("countryDto");
    }

    @Test
    public void shouldReturnErrorWhenNullCustomerDtoContactDetailsDto() {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto();
        customerDto.setContactDetailsDto(null);

        // when
        List<ConstraintViolation<CustomerDto>> violations = validator
                .validate(customerDto).stream().toList();

        // then
        assertThat(violations.size()).isEqualTo(1);
        violations.get(0).getPropertyPath().toString().equals("contactDetailsDto");
    }

    private static CustomerDto getFullFieldCustomerDto() {
        CustomerDto customerDto = getCustomerDtoWithId(1L);
        customerDto.setCountryDto(getCountryDtoWithId(1L));
        customerDto.setContactDetailsDto(getContactDetailsDtoWithId(1L));
        return customerDto;
    }
}
