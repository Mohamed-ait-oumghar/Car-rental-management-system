package com.wheel.wheelhouse.repository;

import com.wheel.wheelhouse.entity.Role;
import com.wheel.wheelhouse.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

   Optional<Role> findByRoleName(String roleName);
   Optional<Role> findById(Long id);

   Page<Role> findAll(Pageable pageable);

}