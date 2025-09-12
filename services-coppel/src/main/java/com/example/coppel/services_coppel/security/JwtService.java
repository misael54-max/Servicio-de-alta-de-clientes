package com.example.coppel.services_coppel.security;

import java.time.Instant;
import java.util.Date;

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

    public JwtService(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.issuer:auth-services}") String issuer
    ) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.issuer = issuer;
        this.verifier = JWT.require(algorithm).withIssuer(issuer).build();
    }

    public DecodedJWT verify(String token) {
        return verifier.verify(token);
    }

    public boolean isExpired(DecodedJWT jwt) {
        Date exp = jwt.getExpiresAt();
        return exp != null && exp.toInstant().isBefore(Instant.now());
    }
}

