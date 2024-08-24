package com.iprody.customerservice.services;

import com.iprody.customerservice.dto.country.CountryDto;
import com.iprody.customerservice.mappers.CountryMapper;
import com.iprody.customerservice.repositories.CountryRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    @Override
    public CountryDto save(CountryDto customerDto) {
        return Optional
                .ofNullable(customerDto)
                .map(countryMapper::toCountry)
                .map(countryRepository::save)
                .map(countryMapper::toCountryDto)
                .orElseThrow(IllegalArgumentException::new);
    }
}
