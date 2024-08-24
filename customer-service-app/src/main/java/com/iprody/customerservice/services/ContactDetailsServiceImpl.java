package com.iprody.customerservice.services;

import com.iprody.customerservice.dto.contactdetails.ContactDetailsDto;
import com.iprody.customerservice.mappers.ContactDetailsMapper;
import com.iprody.customerservice.repositories.ContactDetailsRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactDetailsServiceImpl implements ContactDetailsService {

    private final ContactDetailsMapper contactDetailsMapper;

    private final ContactDetailsRepository contactDetailsRepository;

    @Override
    public ContactDetailsDto save(ContactDetailsDto contactDetailsDto) {
        return Optional
                .ofNullable(contactDetailsDto)
                .map(contactDetailsMapper::toContactDetails)
                .map(contactDetailsRepository::save)
                .map(contactDetailsMapper::toContactDetailsDto)
                .orElseThrow(IllegalArgumentException::new);
    }
}
