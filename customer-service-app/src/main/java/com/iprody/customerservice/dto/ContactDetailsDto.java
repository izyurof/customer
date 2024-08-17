package com.iprody.customerservice.dto;

import static com.iprody.customerservice.utils.ValidationRegex.EMAIL_REGEX;
import static com.iprody.customerservice.utils.ValidationRegex.TELEGRAM_ID_REGEX;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ContactDetailsDto {

    private Long id;

    @NotNull
    @Email(regexp = EMAIL_REGEX, message = "This is not an email")
    private String email;

    @NotNull
    @Pattern(
            regexp = TELEGRAM_ID_REGEX,
            message = "This is not a telegram id"
    )
    private String telegramId;

    private Instant createdAt;

    private Instant updatedAt;

}


