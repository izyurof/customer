package com.iprody.customerservice.validation;

import static com.iprody.customerservice.utils.builder.ContactDetailsDtoBuilder.getContactDetailsDtoWithId;
import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.assertj.core.api.Assertions.assertThat;

import com.iprody.customerservice.dto.contactdetails.ContactDetailsDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class ContactDetailsDtoValidationTests {

    private Validator validator = buildDefaultValidatorFactory().getValidator();

    @Test
    public void shouldReturnNoErrorsWhenValidContactDetailsDto() {
        // given
        ContactDetailsDto contactDetailsDto = getContactDetailsDtoWithId(1L);

        // when
        Set<ConstraintViolation<ContactDetailsDto>> validate =
                validator.validate(contactDetailsDto);

        // then
        assertThat(validate).isEmpty();
    }

    @Test
    public void shouldReturnErrorWhenNullContactDetailsDtoEmail() {
        // given
        ContactDetailsDto contactDetailsDto = getContactDetailsDtoWithId(1L);
        contactDetailsDto.setEmail(null);

        // when
        List<ConstraintViolation<ContactDetailsDto>> violations =
                validator.validate(contactDetailsDto).stream().toList();

        // then
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations.get(0).getPropertyPath().toString()).isEqualTo("email");
    }

    @Test
    public void shouldReturnErrorWhenContactDetailsDtoEmailDoesNotMatchRegex() {
        // given
        ContactDetailsDto contactDetailsDto = getContactDetailsDtoWithId(1L);
        contactDetailsDto.setEmail("abc@gmailcom/-++--efwgrt");

        // when
        List<ConstraintViolation<ContactDetailsDto>> violations =
                validator.validate(contactDetailsDto).stream().toList();

        // then
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations.get(0).getPropertyPath().toString()).isEqualTo("email");
    }

    @Test
    public void shouldReturnErrorWhenNullContactDetailsDtoTelegramId() {
        // given
        ContactDetailsDto contactDetailsDto = getContactDetailsDtoWithId(1L);
        contactDetailsDto.setTelegramId(null);

        // when
        List<ConstraintViolation<ContactDetailsDto>> violations =
                validator.validate(contactDetailsDto).stream().toList();

        // then
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations.get(0).getPropertyPath().toString()).isEqualTo("telegramId");
    }

    @Test
    public void shouldReturnErrorWhenContactDetailsDtoTelegramIdDoesNotMatchRegex() {
        // given
        ContactDetailsDto contactDetailsDto = getContactDetailsDtoWithId(1L);
        contactDetailsDto.setTelegramId("a@dghthrgshtgf");

        // when
        List<ConstraintViolation<ContactDetailsDto>> violations =
                validator.validate(contactDetailsDto).stream().toList();

        // then
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations.get(0).getPropertyPath().toString()).isEqualTo("telegramId");
    }

}
