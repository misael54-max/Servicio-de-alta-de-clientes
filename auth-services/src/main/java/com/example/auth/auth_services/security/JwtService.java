package com.example.auth.auth_services.security;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Service
public class JwtService {
    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final String issuer;
    private final long expirationMinutes;

    public JwtService(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.issuer:auth-services}") String issuer,
        @Value("${jwt.expiration-minutes:60}") long expirationMinutes) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.issuer = issuer;
        this.expirationMinutes = expirationMinutes;
        this.verifier = JWT.require(algorithm).withIssuer(issuer).build();
    }

    public String generateToken(String subject, List<String> roles) {
        Instant now = Instant.now();
        return JWT.create()
            .withIssuer(issuer)
            .withSubject(subject)
            .withIssuedAt(Date.from(now))
            .withExpiresAt(Date.from(now.plusSeconds(expirationMinutes * 60)))
            .withClaim("roles", roles)
            .sign(algorithm);
    }

    public DecodedJWT verify(String token) {
        return verifier.verify(token);
    }
}

