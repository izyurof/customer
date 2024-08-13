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

    @NotNull
    private Long id;

    @NotNull
    @Email(regexp = "^(?=.{1,256})(?=.{1,64}@.{1,255}$)(?=.{1,63}"
            + "\\.[A-Za-z]{2,63}$)(?!.*[.]{2})[A-Za-z0-9+_.-]+"
            + "@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "This is not an email")
    private String email;

    @NotNull
    @Pattern(
            regexp = "^@[a-zA-Z0-9_]{4,31}$",
            message = "This is not a telegram id"
    )
    private String telegramId;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;

}


