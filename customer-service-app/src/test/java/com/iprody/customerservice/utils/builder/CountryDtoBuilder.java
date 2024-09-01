package com.iprody.customerservice.utils.builder;

import com.iprody.customerservice.dto.country.CountryDto;

public class CountryDtoBuilder {

    public static CountryDto getCountryDtoWithId(Long id) {
        if (id == 1) {
            CountryDto countryDto = new CountryDto();
            countryDto.setId(1L);
            countryDto.setCountryCode("AIA");
            countryDto.setName("Anguilla");
            return countryDto;
        }

        CountryDto countryDto = new CountryDto();
        countryDto.setId(2L);
        countryDto.setCountryCode("AGO");
        countryDto.setName("Angola");
        return countryDto;
    }

}
