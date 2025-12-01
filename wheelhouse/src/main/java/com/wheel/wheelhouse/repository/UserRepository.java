package com.wheel.wheelhouse.repository;

import com.wheel.wheelhouse.dto.UserDto;
import com.wheel.wheelhouse.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String userName);
    //Pagination
    Page<User> findAll(Pageable pageable);

}
