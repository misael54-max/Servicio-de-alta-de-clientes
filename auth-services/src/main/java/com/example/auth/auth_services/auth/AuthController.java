package com.example.auth.auth_services.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.auth_services.security.JwtService;
import com.example.auth.auth_services.user.UserEntity;
import com.example.auth.auth_services.user.UserRepository;
import com.example.auth.auth_services.user.UserTypeEntity;

import lombok.Data;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String role = user.getUserType() != null ? user.getUserType().getName() : "USER";
        String token = jwtService.generateToken(user.getEmail(), List.of("ROLE_" + role.toUpperCase()));
        return new TokenResponse(token);
    }

    // Quick helper to register users for testing
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "User already exists";
        }
        //Crea un objeto UserTypeEntity con el ID proporcionado o el valor predeterminado (2Long)
        UserTypeEntity userType = UserTypeEntity.builder().id(request.getUserType() != null ? request.getUserType().longValue() : 2L).build();
        
        UserEntity user = UserEntity.builder()
            .name(request.getName())
            .email(request.getEmail())
            .userPassword(passwordEncoder.encode(request.getPassword()))
            .userType(userType)
            .build();

        userRepository.save(user);
        return "OK";
    }

    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Data
    public static class RegisterRequest {
        private String name;
        private String email;
        private String password;
        private Integer userType; // Optional: to set user type during registration
    }

    @Data
    public static class TokenResponse {
        private final String token;
    }
}

