package com.iprody.customerservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contactdetails")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ContactDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contactdetails_seq"
    )
    @SequenceGenerator(
            name = "contactdetails_seq",
            sequenceName = "contactdetails_seq",
            allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "telegram_id")
    private String telegramId;

    @OneToOne(mappedBy = "contactDetails")
    private Customer customer;

    @Column(name = "createdat")
    private ZonedDateTime createdAt;

    @Column(name = "updatedat")
    private ZonedDateTime updatedAt;
}
