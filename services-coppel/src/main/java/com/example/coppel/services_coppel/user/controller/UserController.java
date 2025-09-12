package com.example.coppel.services_coppel.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coppel.services_coppel.user.IUserService;

import lombok.RequiredArgsConstructor;
import java.util.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.GetMapping;
import com.example.coppel.services_coppel.user.model.UserEntity;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);
    @Autowired
    private final IUserService userService;
    
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("all")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }
    
}
