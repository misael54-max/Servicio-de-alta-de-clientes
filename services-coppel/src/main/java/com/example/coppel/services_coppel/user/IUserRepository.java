package com.example.coppel.services_coppel.user;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.coppel.services_coppel.user.model.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long> { }