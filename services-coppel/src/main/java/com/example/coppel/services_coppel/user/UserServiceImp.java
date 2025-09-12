package com.example.coppel.services_coppel.user;

import org.springframework.stereotype.Service;

import com.example.coppel.services_coppel.user.model.UserEntity;

import lombok.RequiredArgsConstructor;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements IUserService {
    private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserServiceImp.class);
    private final IUserRepository userRepository;
    
    @Override
    public List<UserEntity> getAllUsers() {
        log.info("Fetching all users from the database");
        return userRepository.findAll();
    }

    
}
