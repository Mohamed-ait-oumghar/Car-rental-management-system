package com.wheel.wheelhouse.repository;

import com.wheel.wheelhouse.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    List<Client> findByClientFirstName(String clientFirstName);
    Optional<Client> findByPhoneNumber(String phoneNumber);
    Optional<Client> findByCin(String cin);

}
