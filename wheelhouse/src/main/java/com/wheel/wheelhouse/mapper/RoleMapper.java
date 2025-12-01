package com.wheel.wheelhouse.mapper;

import com.wheel.wheelhouse.dto.RoleDto;
import com.wheel.wheelhouse.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {


    public static RoleDto toDto(Role role) {
        if (role == null) return null;

        RoleDto dto = new RoleDto();
        dto.setRoleName(role.getRoleName());
        return dto;
    }


    public static Role toEntity(RoleDto roleDto) {
        if (roleDto == null) return null;

        Role role = new Role();
        role.setRoleName(roleDto.getRoleName());
        return role;
    }
}
