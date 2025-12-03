package com.wheel.wheelhouse.controller;

import com.wheel.wheelhouse.dto.UserDto;
import com.wheel.wheelhouse.entity.User;
import com.wheel.wheelhouse.mapper.UserMapper;
import com.wheel.wheelhouse.securityconfig.JwtUtils;
import com.wheel.wheelhouse.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create user
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        User savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(UserMapper.toDto(savedUser), HttpStatus.CREATED);
    }

    // Get all users with pagination
    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserDto> users = userService.getAllUsers(pageable);

        return ResponseEntity.ok(users);
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return ResponseEntity.ok(UserMapper.toDto(user));
    }
    // Get us by email
    @GetMapping("/search/email")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(value -> ResponseEntity.ok(UserMapper.toDto(value)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Update user by username
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto updateUser) {
        UserDto updatedUser = userService.updateUser(id, updateUser);
        return ResponseEntity.ok(updatedUser);
    }
    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }


}