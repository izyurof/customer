package com.iprody.customerservice.validation;

import static com.iprody.customerservice.utils.builder.CustomerDtoBuilder.getCustomerDtoWithId;
import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.assertj.core.api.Assertions.assertThat;

import com.iprody.customerservice.dto.CountryDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CountryDtoValidationTests {

    private Validator validator;

    @BeforeAll
    public void initValidator() {
        ValidatorFactory factory = buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldReturnNoErrorsWhenValidCountryDto() {
        // given
        CountryDto countryDto = getCustomerDtoWithId().getCountryDto();

        // when
        Set<ConstraintViolation<CountryDto>> validate =
                validator.validate(countryDto);

        // then
        assertThat(validate).isEmpty();
    }

    @Test
    public void shouldReturnErrorWhenNullCountryDtoId() {
        // given
        CountryDto countryDto = new CountryDto();

        // when
        Set<ConstraintViolation<CountryDto>> validate = validator.validate(countryDto);
        long id = validate.stream()
                .filter(
                        violation -> violation.getPropertyPath().toString().equals("id")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorWhenNullCountryDtoName() {
        // given
        CountryDto countryDto = new CountryDto();

        // when
        Set<ConstraintViolation<CountryDto>> validate = validator.validate(countryDto);
        long id = validate.stream()
                .filter(
                        violation -> violation.getPropertyPath().toString().equals("name")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorWhenNullCountryDtoNameLongerThan60Characters() {
        // given
        CountryDto countryDto = new CountryDto();
        countryDto.setName("edtfggtkujtyhtgftgyjhlkyjgtgyhghggyhjklkhgfghjlukjhythhjikyujyh");

        // when
        Set<ConstraintViolation<CountryDto>> validate = validator.validate(countryDto);
        long id = validate.stream()
                .filter(
                        violation -> violation.getPropertyPath().toString().equals("name")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorWhenNullCountryDtoCountryCode() {
        // given
        CountryDto countryDto = new CountryDto();

        // when
        Set<ConstraintViolation<CountryDto>> validate = validator.validate(countryDto);
        long id = validate.stream()
                .filter(
                        violation -> violation.getPropertyPath().toString().equals("countryCode")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorWhenCountryDtoCountryCodeNotThreeUppercaseCharacter() {
        // given
        CountryDto countryDto = new CountryDto();
        countryDto.setCountryCode("abc");

        // when
        Set<ConstraintViolation<CountryDto>> validate = validator.validate(countryDto);
        long id = validate.stream()
                .filter(
                        violation -> violation.getPropertyPath().toString().equals("countryCode")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorWhenNullCountryDtoCreatedAt() {
        // given
        CountryDto countryDto = new CountryDto();

        // when
        Set<ConstraintViolation<CountryDto>> validate = validator.validate(countryDto);
        long id = validate.stream()
                .filter(
                        violation -> violation.getPropertyPath().toString().equals("createdAt")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorWhenNullCountryDtoUpdatedAt() {
        // given
        CountryDto countryDto = new CountryDto();

        // when
        Set<ConstraintViolation<CountryDto>> validate = validator.validate(countryDto);
        long id = validate.stream()
                .filter(
                        violation -> violation.getPropertyPath().toString().equals("updatedAt")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }
}
