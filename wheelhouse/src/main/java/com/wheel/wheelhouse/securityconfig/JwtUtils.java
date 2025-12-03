package com.wheel.wheelhouse.securityconfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    private final String jwtSecret = "mySecretKeywrwfwefqwrfqrfwrwfemySecretKeywrwfwefqwrfqrfwrwfemySecretKeywrwfwefqwrfqrfwrwfemySecretKeywrwfwefqwrfqrfwrwfemySecretKeywrwfwefqwrfqrfwrwfemySecretKeywrwfwefqwrfqrfwrwfemySecretKeywrwfwefqwrfqrfwrwfe";
    private final long jwtExpirationMs = 86400000; // 1 day

    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles) // now 'roles' comes from the method parameter
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

}
