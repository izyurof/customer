package com.iprody.customerservice.dto;

import jakarta.validation.constraints.NotNull;
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
public class CustomerDto {

    @NotNull
    private Long id;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Size(max = 30)
    private String surname;

    @NotNull
    private CountryDto countryDto;

    @NotNull
    private ContactDetailsDto contactDetailsDto;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;
    
}



