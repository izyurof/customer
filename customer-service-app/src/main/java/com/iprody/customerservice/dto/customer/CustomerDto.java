package com.iprody.customerservice.dto.customer;

import com.iprody.customerservice.dto.contactdetails.ContactDetailsDto;
import com.iprody.customerservice.dto.country.CountryDto;
import jakarta.validation.Valid;
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

    private Long id;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Size(max = 30)
    private String surname;

    @NotNull
    @Valid
    private CountryDto countryDto;

    @NotNull
    @Valid
    private ContactDetailsDto contactDetailsDto;

    private Instant createdAt;

    private Instant updatedAt;
    
}



