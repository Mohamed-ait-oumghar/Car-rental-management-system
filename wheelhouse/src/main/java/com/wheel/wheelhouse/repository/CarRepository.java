package com.wheel.wheelhouse.repository;

import com.wheel.wheelhouse.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    Optional<Car> findByMark(String mark);
    Optional<Car> findById(Long id);
    Optional<Car> findByModule(String module);
    Optional<Car> findByPlateNumber(String plateNumber);

}
