package com.wheel.wheelhouse.repository;

import com.wheel.wheelhouse.entity.Client;
import com.wheel.wheelhouse.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByCreationDate(LocalDate creationDate);

    boolean existsByClient_ClientId(Long clientId);

    boolean existsByUser_UserId(Long userId);

    boolean existsByCar_CarId(Long carId);

    Page<Order> findAll(Pageable pageable);

}
