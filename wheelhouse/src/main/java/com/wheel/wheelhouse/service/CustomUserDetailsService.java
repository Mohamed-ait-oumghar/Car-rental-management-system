package com.wheel.wheelhouse.service;

import com.wheel.wheelhouse.entity.Role;
import com.wheel.wheelhouse.entity.User;
import com.wheel.wheelhouse.repository.RoleRepository;
import com.wheel.wheelhouse.repository.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public CustomUserDetailsService(UserRepository userRepository,
                                    PasswordEncoder passwordEncoder,
                                    RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: " + username));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                authorities
        );
    }

    public User registerNewUserUser(String username, String email, String rawPassword, Set<String> rolesName) {
        User user = new User();
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));

        if (rolesName != null && !rolesName.isEmpty()) {
            Set<Role> roles = rolesName.stream()
                    .map(roleName -> roleRepository.findByRoleName(roleName)
                            .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }

        return userRepository.save(user);
    }
}
