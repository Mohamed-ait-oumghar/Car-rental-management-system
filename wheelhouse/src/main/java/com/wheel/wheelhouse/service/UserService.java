package com.wheel.wheelhouse.service;

import com.wheel.wheelhouse.customException.UserDeletionException;
import com.wheel.wheelhouse.dto.UserDto;
import com.wheel.wheelhouse.entity.Role;
import com.wheel.wheelhouse.entity.User;
import com.wheel.wheelhouse.mapper.UserMapper;
import com.wheel.wheelhouse.repository.OrderRepository;
import com.wheel.wheelhouse.repository.RoleRepository;
import com.wheel.wheelhouse.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

     UserRepository userRepository;
     RoleRepository roleRepository;
     OrderRepository orderRepository;


    public UserService(UserRepository userRepository, RoleRepository roleRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.orderRepository = orderRepository;
    }

    // Create user with role names
    public User createUser(UserDto dto) {
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());

        if (dto.getRolesName() != null && !dto.getRolesName().isEmpty()) {

            Set<Role> roles = new HashSet<>();

            for (String roleName : dto.getRolesName()) {

                Role role = roleRepository.findByRoleName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));

                roles.add(role);
            }
            user.setRoles(roles);
        }
        return userRepository.save(user);
    }

    //Pagination
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    //Get users by Ids
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    //Get  users by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //Update user (email, password, roles)
    public UserDto updateUser(String userName, UserDto updateUser) {

        User existingUser = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userName));

        if (updateUser.getEmail() != null) {
            existingUser.setEmail(updateUser.getEmail());
        }

        if (updateUser.getPassword() != null && !updateUser.getPassword().isEmpty()) {
            existingUser.setPassword(updateUser.getPassword());
        }

        if (updateUser.getRolesName() != null) {
            Set<Role> roles = new HashSet<>();
            for (String roleName : updateUser.getRolesName()) {
                Role role = roleRepository.findByRoleName(roleName)
                        .orElseThrow(() ->
                                new RuntimeException("Role not found: " + roleName));
                roles.add(role);
            }
            existingUser.setRoles(roles);
        }
        User saved = userRepository.save(existingUser);
        return UserMapper.toDto(saved);
    }

    //delete
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        if (orderRepository.existsByUser_UserId(id)) {
            throw new UserDeletionException(
                    "Cannot delete user with id " + id + " because they are assigned to one or more orders."
            );
        }
        userRepository.deleteById(id);
    }

}