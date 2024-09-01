package com.iprody.customerservice.validation;

import static com.iprody.customerservice.utils.builder.CountryDtoBuilder.getCountryDtoWithId;
import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.assertj.core.api.Assertions.assertThat;

import com.iprody.customerservice.dto.country.CountryDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class CountryDtoValidationTests {

    private Validator validator = buildDefaultValidatorFactory().getValidator();

    @Test
    public void shouldReturnNoErrorsWhenValidCountryDto() {
        // given
        CountryDto countryDto = getCountryDtoWithId(1L);

        // when
        Set<ConstraintViolation<CountryDto>> validate =
                validator.validate(countryDto);

        // then
        assertThat(validate).isEmpty();
    }

    @Test
    public void shouldReturnErrorWhenNullCountryDtoId() {
        // given
        CountryDto countryDto = getCountryDtoWithId(1L);
        countryDto.setId(null);

        // when
        List<ConstraintViolation<CountryDto>> violations = validator
                .validate(countryDto).stream().toList();

        // then
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations.get(0).getPropertyPath().toString()).isEqualTo("id");
    }

    @Test
    public void shouldReturnErrorWhenNullCountryDtoName() {
        // given
        CountryDto countryDto = getCountryDtoWithId(1L);
        countryDto.setName(null);

        // when
        List<ConstraintViolation<CountryDto>> violations = validator
                .validate(countryDto).stream().toList();

        // then
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations.get(0).getPropertyPath().toString()).isEqualTo("name");
    }

    @Test
    public void shouldReturnErrorWhenNullCountryDtoNameLongerThan60Characters() {
        // given
        CountryDto countryDto = getCountryDtoWithId(1L);
        countryDto.setName("edtfggtkujtyhtgftgyjhlkyjgtgyhghggyhjklkhgfghjlukjhythhjikyujyh");

        // when
        List<ConstraintViolation<CountryDto>> violations = validator
                .validate(countryDto).stream().toList();

        // then
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations.get(0).getPropertyPath().toString()).isEqualTo("name");
    }

    @Test
    public void shouldReturnErrorWhenNullCountryDtoCountryCode() {
        // given
        CountryDto countryDto = getCountryDtoWithId(1L);
        countryDto.setCountryCode(null);

        // when
        List<ConstraintViolation<CountryDto>> violations = validator
                .validate(countryDto).stream().toList();

        // then
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations.get(0).getPropertyPath().toString()).isEqualTo("countryCode");
    }

    @Test
    public void shouldReturnErrorWhenCountryDtoCountryCodeNotThreeUppercaseCharacter() {
        // given
        CountryDto countryDto = getCountryDtoWithId(1L);
        countryDto.setCountryCode("abc");

        // when
        List<ConstraintViolation<CountryDto>> violations = validator
                .validate(countryDto).stream().toList();

        // then
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations.get(0).getPropertyPath().toString()).isEqualTo("countryCode");
    }

}
