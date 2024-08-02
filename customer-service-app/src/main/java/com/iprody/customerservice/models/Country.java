package com.iprody.customerservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "countries")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Country {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "countries_seq"
    )
    @SequenceGenerator(
            name = "countries_seq",
            sequenceName = "countries_seq",
            allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country")
    private List<Customer> customers;

    @Column(name = "createdat")
    @CreationTimestamp
    private ZonedDateTime createdAt;

    @Column(name = "updatedat")
    @UpdateTimestamp
    private ZonedDateTime updatedAt;

}
