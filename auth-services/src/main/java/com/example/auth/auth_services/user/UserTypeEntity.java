package com.example.auth.auth_services.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_types", schema = "coppel")
public class UserTypeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pki_user_type")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
}
