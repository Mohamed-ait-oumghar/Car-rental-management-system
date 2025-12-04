package com.wheel.wheelhouse.controller;

import com.wheel.wheelhouse.dto.AuthRequest;
import com.wheel.wheelhouse.dto.AuthResponse;
import com.wheel.wheelhouse.entity.Role;
import com.wheel.wheelhouse.repository.RoleRepository;
import com.wheel.wheelhouse.securityconfig.JwtTokenProvider;
import com.wheel.wheelhouse.service.CustomUserDetailsService;

import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthController(AuthenticationManager authenticationManager, RoleRepository roleRepository, JwtTokenProvider tokenProvider, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.tokenProvider = tokenProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication.getName());

        return new AuthResponse(token);
    }


    @PostMapping("/register")
    public String register(@Valid @RequestBody AuthRequest request) {
        if (request.getRolesName() == null || request.getRolesName().isEmpty()) {
            throw new IllegalArgumentException("User must have at least one role.");
        }

        customUserDetailsService.registerNewUser(
                request.getUserName(),
                request.getPassword(),
                request.getRolesName()
        );

        return "User registered successfully!";
    }


}

