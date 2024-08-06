package com.iprody.customerservice.repositories;

import com.iprody.customerservice.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>,
        JpaSpecificationExecutor<Customer> {

    @Modifying(flushAutomatically = true)
    @Query("""
            update Customer c set c.name = :name where c.id = :id
            """)
    void updateCustomerName(@Param("id") Long id, @Param("name") String name);

    @Modifying(flushAutomatically = true)
    @Query("""
            update Customer c set c.surname = :surname where c.id = :id
            """)
    void updateCustomerSurname(@Param("id") Long id, @Param("surname") String surname);

    @Modifying(flushAutomatically = true)
    @Query("""
            update ContactDetails cd
            set cd.email = :email
            where cd.id = (
            select c.contactDetails.id
            from Customer c
            where c.id = :id
            )
            """)
    void updateCustomerEmail(@Param("id") Long id, @Param("email") String email);

    @Modifying(flushAutomatically = true)
    @Query("""
            update ContactDetails cd
            set cd.telegramId = :telegramId
            where cd.id = (
            select c.contactDetails.id
            from Customer c
            where c.id = :id
            )
            """)
    void updateCustomerTelegramId(Long id, String telegramId);

    @Modifying(flushAutomatically = true)
    @Query("""
            update Customer c
            set c.country = (select co from Country co where co.name = :name)
            where c.id = :id
            """)
    void updateCustomerCountry(Long id, String name);
}
