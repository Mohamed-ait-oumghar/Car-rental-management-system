package com.wheel.wheelhouse.securityconfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtils {

    private final String jwtSecret = "mySecretKeywrwfwefqwrfqrfwrwfemySecretKeywrwfwefqwrfqrfwrwfemySecretKeywrwfwefqwrfqrfwrwfemySecretKeywrwfwefqwrfqrfwrwfemySecretKeywrwfwefqwrfqrfwrwfemySecretKeywrwfwefqwrfqrfwrwfemySecretKeywrwfwefqwrfqrfwrwfe";
    private final long jwtExpirationMs = 86400000; // 1 day

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
