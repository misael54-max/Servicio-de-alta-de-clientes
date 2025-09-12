package com.example.coppel.services_coppel.country.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "countries", schema = "coppel")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pki_country")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;
}
