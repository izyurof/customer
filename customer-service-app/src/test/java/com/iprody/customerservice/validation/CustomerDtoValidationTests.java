package com.iprody.customerservice.validation;

import static com.iprody.customerservice.utils.builder.CustomerDtoBuilder.getCustomerDtoWithId;
import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.assertj.core.api.Assertions.assertThat;

import com.iprody.customerservice.dto.customer.CustomerDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerDtoValidationTests {

    private Validator validator;

    @BeforeAll
    public void initValidator() {
        ValidatorFactory factory = buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldReturnNoErrorsWhenValidCustomerDto() {
        // given
        CustomerDto customerDto = getCustomerDtoWithId();

        // when
        Set<ConstraintViolation<CustomerDto>> validate =
                validator.validate(customerDto);

        // then
        assertThat(validate).isEmpty();
    }

    @Test
    public void shouldReturnErrorWhenNullCustomerDtoName() {
        // given
        CustomerDto customerDto = new CustomerDto();

        // when
        Set<ConstraintViolation<CustomerDto>> validate = validator.validate(customerDto);
        long id = validate.stream()
                .filter(
                        violation -> violation
                                .getPropertyPath()
                                .toString()
                                .equals("name")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorWhenCustomerDtoNameLongerThan30Characters() {
        // given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("dtgfyhgiykyjtgfyikggujtfhghykyhikhujgyh");

        // when
        Set<ConstraintViolation<CustomerDto>> validate = validator.validate(customerDto);
        long id = validate.stream()
                .filter(
                        violation -> violation
                                .getPropertyPath()
                                .toString()
                                .equals("name")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorWhenNullCustomerDtoSurname() {
        // given
        CustomerDto customerDto = new CustomerDto();

        // when
        Set<ConstraintViolation<CustomerDto>> validate = validator.validate(customerDto);
        long id = validate.stream()
                .filter(
                        violation -> violation
                                .getPropertyPath()
                                .toString()
                                .equals("surname")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorWhenCustomerDtoSurnameLongerThan30Characters() {
        // given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setSurname("dtgfyhgiykyjtgfyikggujtfhghykyhikhujgyh");

        // when
        Set<ConstraintViolation<CustomerDto>> validate = validator.validate(customerDto);
        long id = validate.stream()
                .filter(
                        violation -> violation
                                .getPropertyPath()
                                .toString()
                                .equals("name")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorWhenNullCustomerDtoCountryDto() {
        // given
        CustomerDto customerDto = new CustomerDto();

        // when
        Set<ConstraintViolation<CustomerDto>> validate = validator.validate(customerDto);
        long id = validate.stream()
                .filter(
                        violation -> violation
                                .getPropertyPath()
                                .toString()
                                .equals("countryDto")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorWhenNullCustomerDtoContactDetailsDto() {
        // given
        CustomerDto customerDto = new CustomerDto();

        // when
        Set<ConstraintViolation<CustomerDto>> validate = validator.validate(customerDto);
        long id = validate.stream()
                .filter(
                        violation -> violation
                                .getPropertyPath()
                                .toString()
                                .equals("contactDetailsDto")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

}
