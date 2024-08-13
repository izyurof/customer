package com.iprody.customerservice.dto;

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
            regexp = "^[A-Z]{3}$",
            message = "Country code must be exactly 3 uppercase English letters"
    )
    private String countryCode;

    @NotNull
    @Size(max = 60)
    private String name;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;

}



