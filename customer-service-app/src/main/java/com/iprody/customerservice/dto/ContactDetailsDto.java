package com.iprody.customerservice.dto;

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

    private static final String EMAIL_REGEX = "^(?=.{1,256})(?=.{1,64}@.{1,255}$)(?=.{1,63}"
            + "\\.[A-Za-z]{2,63}$)(?!.*[.]{2})[A-Za-z0-9+_.-]+"
            + "@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private static final String TELEGRAM_ID_REGEX = "^@[a-zA-Z0-9_]{4,31}$";

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


