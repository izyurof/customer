package com.iprody.customerservice.specifications;

import com.iprody.customerservice.models.Country;
import com.iprody.customerservice.models.Customer;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {

    public static Specification<Customer> filterByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("name"), name);
        };
    }

    public static Specification<Customer> filterBySurname(String surname) {
        return (root, query, criteriaBuilder) -> {
            if (surname == null || surname.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("surname"), surname);
        };
    }

    public static Specification<Customer> filterByCountryName(String countryName) {
        return ((root, query, criteriaBuilder) -> {
            if (countryName == null || countryName.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Customer, Country> countryJoin = root.join("country");
            return criteriaBuilder.equal(countryJoin.get("name"), countryName);
        });
    }

    public static Specification<Customer> orderByCountryName(String sortDirection) {
        return (root, query, criteriaBuilder) -> {
            if (sortDirection != null && !sortDirection.isEmpty()) {
                Join<Customer, Country> countryJoin = root.join("country");
                if (sortDirection.equalsIgnoreCase("asc")) {
                    query.orderBy(criteriaBuilder.asc(countryJoin.get("name")));
                } else if (sortDirection.equalsIgnoreCase("desc")) {
                    query.orderBy(criteriaBuilder.desc(countryJoin.get("name")));
                }
            }
            return criteriaBuilder.conjunction();
        };
    }
}
