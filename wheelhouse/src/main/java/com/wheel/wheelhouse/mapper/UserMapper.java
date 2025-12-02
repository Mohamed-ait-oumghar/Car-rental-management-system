package com.wheel.wheelhouse.mapper;


import com.wheel.wheelhouse.dto.UserDto;
import com.wheel.wheelhouse.entity.Role;
import com.wheel.wheelhouse.entity.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    // Entity -> DTO
    public static UserDto toDto(User user) {
        if (user == null) return null;

        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        Set<String> roleNames = user.getRoles()
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());
        return userDto;
    }

    // DTO -> Entity
    public static User toEntity(UserDto userDto) {
        if (userDto == null) return null;

        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        return user;
    }
}

















