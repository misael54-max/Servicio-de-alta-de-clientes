package com.example.coppel.services_coppel.customer.model;

import com.example.coppel.services_coppel.country.model.CountryEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customers", schema = "coppel")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pki_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    // Relación con CountryEntity (FK fki_country)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fki_country", referencedColumnName = "pki_country")
    private CountryEntity country;

}
