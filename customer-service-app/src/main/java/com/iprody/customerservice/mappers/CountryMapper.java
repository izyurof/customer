package com.iprody.customerservice.mappers;

import com.iprody.customerservice.dto.CountryDto;
import com.iprody.customerservice.models.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CountryMapper {

    @Mappings({
        @Mapping(source = "createdAt", target = "createdAt"),
        @Mapping(source = "updatedAt", target = "updatedAt")
    })
    CountryDto toCountryDto(Country country);

    @Mappings({
        @Mapping(source = "createdAt", target = "createdAt"),
        @Mapping(source = "updatedAt", target = "updatedAt")
    })
    Country toCountry(CountryDto countryDto);

}
