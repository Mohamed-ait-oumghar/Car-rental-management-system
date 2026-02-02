package com.wheel.wheelhouse.controller;

import com.wheel.wheelhouse.dto.LoginRequest;
import com.wheel.wheelhouse.dto.RegisterRequest;
import com.wheel.wheelhouse.dto.AuthResponse;
import com.wheel.wheelhouse.securityconfig.JwtTokenProvider;
import com.wheel.wheelhouse.service.CustomUserDetailsService;

import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.customUserDetailsService = customUserDetailsService;
    }
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        String token = tokenProvider.generateToken(authentication.getName(), roles);
        return new AuthResponse(token);
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {

        customUserDetailsService.registerNewUserUser(
                request.getUserName(),
                request.getEmail(),
                request.getPassword(),
                request.getRolesName()
        );
        return "User registered successfully!";
    }


}

