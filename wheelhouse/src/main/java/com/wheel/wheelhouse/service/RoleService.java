package com.wheel.wheelhouse.service;

import com.wheel.wheelhouse.dto.RoleDto;
import com.wheel.wheelhouse.dto.UserDto;
import com.wheel.wheelhouse.entity.Role;

import com.wheel.wheelhouse.entity.User;
import com.wheel.wheelhouse.mapper.RoleMapper;
import com.wheel.wheelhouse.mapper.UserMapper;
import com.wheel.wheelhouse.repository.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class RoleService {

    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    //Create or update a Role
    public Role createRole(Role role){
        return roleRepository.save(role);
    }

    //Pagination
    public Page<RoleDto> getAllRoles(Pageable pageable) {

        Page<Role> rolesPage = roleRepository.findAll(pageable);

        return rolesPage.map(RoleMapper::toDto);
    }

    // find by name
    public Optional<Role> getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    //Delete Role
    public void deleteRole(Long id) {
        if(!roleRepository.existsById(id)){
            throw new RuntimeException("Can not delete. Role not found with id:" + id);
        }
        roleRepository.deleteById(id);
    }
}
