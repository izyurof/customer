package com.iprody.customerservice.validation;

import static com.iprody.customerservice.utils.builder.CustomerDtoBuilder.getCustomerDtoWithId;
import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.assertj.core.api.Assertions.assertThat;

import com.iprody.customerservice.dto.ContactDetailsDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactDetailsDtoValidationTests {

    private Validator validator;

    @BeforeAll
    public void initValidator() {
        ValidatorFactory factory = buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldReturnNoErrorsWhenValidContactDetailsDto() {
        // given
        ContactDetailsDto contactDetailsDto =
                getCustomerDtoWithId().getContactDetailsDto();

        // when
        Set<ConstraintViolation<ContactDetailsDto>> validate =
                validator.validate(contactDetailsDto);

        // then
        assertThat(validate).isEmpty();
    }

    @Test
    public void shouldReturnErrorWhenNullContactDetailsDtoEmail() {
        // given
        ContactDetailsDto contactDetailsDto = new ContactDetailsDto();

        // when
        Set<ConstraintViolation<ContactDetailsDto>> validate =
                validator.validate(contactDetailsDto);
        long id = validate.stream()
                .filter(
                        violation -> violation.getPropertyPath().toString().equals("email")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorWhenContactDetailsDtoEmailDoesNotMatchRegex() {
        // given
        ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
        contactDetailsDto.setEmail("abc@gmailcom/-++--efwgrt");

        // when
        Set<ConstraintViolation<ContactDetailsDto>> validate =
                validator.validate(contactDetailsDto);
        long id = validate.stream()
                .filter(
                        violation -> violation.getPropertyPath().toString().equals("email")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorWhenNullContactDetailsDtoTelegramId() {
        // given
        ContactDetailsDto contactDetailsDto = new ContactDetailsDto();

        // when
        Set<ConstraintViolation<ContactDetailsDto>> validate =
                validator.validate(contactDetailsDto);
        long id = validate.stream()
                .filter(
                        violation -> violation.getPropertyPath().toString().equals("telegramId")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void shouldReturnErrorWhenContactDetailsDtoTelegramIdDoesNotMatchRegex() {
        // given
        ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
        contactDetailsDto.setTelegramId("a@dghthrgshtgf");

        // when
        Set<ConstraintViolation<ContactDetailsDto>> validate =
                validator.validate(contactDetailsDto);
        long id = validate.stream()
                .filter(
                        violation -> violation.getPropertyPath().toString().equals("telegramId")
                )
                .count();

        // then
        assertThat(id).isEqualTo(1);
    }

}
