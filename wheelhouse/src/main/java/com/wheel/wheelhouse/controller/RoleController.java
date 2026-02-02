package com.wheel.wheelhouse.controller;

import com.wheel.wheelhouse.dto.RoleDto;
import com.wheel.wheelhouse.dto.UserDto;
import com.wheel.wheelhouse.entity.Role;
import com.wheel.wheelhouse.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class  RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Create
    @PostMapping
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role) {
        Role createdRole = roleService.createRole(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    // Get all roles with pagination
    @GetMapping
    public ResponseEntity<Page<RoleDto>> getAllRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        Page<RoleDto> users = roleService.getAllRoles(pageable);

        return ResponseEntity.ok(users);
    }

    // Get role by name
    @GetMapping("/search/{roleName}")
    public ResponseEntity<Role> getRoleByName(@PathVariable @RequestParam String roleName) {
        Optional<Role> role = roleService.getRoleByName(roleName);
        return role.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        return role.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok("Role deleted successfully");
    }
}