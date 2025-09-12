package com.example.auth.auth_services.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users", schema = "coppel")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pki_user")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "password", length = 100)
    private String userPassword;

    // Relación con CountryEntity (FK fki_country)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fki_user_type", referencedColumnName = "pki_user_type")
    private UserTypeEntity userType;
}
