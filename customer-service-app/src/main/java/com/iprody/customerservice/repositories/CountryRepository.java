package com.iprody.customerservice.repositories;

import com.iprody.customerservice.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CountryRepository extends JpaRepository<Country, Long>,
        JpaSpecificationExecutor<Country> {

    Country findCountryByName(String countryName);

}
