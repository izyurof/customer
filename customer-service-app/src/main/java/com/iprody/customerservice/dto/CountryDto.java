package com.iprody.customerservice.dto;

import static com.iprody.customerservice.utils.ValidationRegex.COUNTRY_CODE_REGEX;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class CountryDto {

    @NotNull
    private Long id;

    @NotNull
    @Pattern(
            regexp = COUNTRY_CODE_REGEX,
            message = "Country code must be exactly 3 uppercase English letters"
    )
    private String countryCode;

    @NotNull
    @Size(max = 60)
    private String name;

    private Instant createdAt;

    private Instant updatedAt;

}



