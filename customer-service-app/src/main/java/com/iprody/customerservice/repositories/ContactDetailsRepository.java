package com.iprody.customerservice.repositories;

import com.iprody.customerservice.models.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {

}
