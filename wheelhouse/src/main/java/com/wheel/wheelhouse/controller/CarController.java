package com.wheel.wheelhouse.controller;

import com.wheel.wheelhouse.dto.CarDto;
import com.wheel.wheelhouse.dto.RoleDto;
import com.wheel.wheelhouse.entity.Car;
import com.wheel.wheelhouse.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // Create
    @PostMapping
    public ResponseEntity<Car> createCar(@Valid @RequestBody CarDto carDto) {
        Car createdCar = carService.createCar(carDto);
        return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
    }

    // Get all Cars with pagination
    @GetMapping
    public ResponseEntity<Page<CarDto>> getAllRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        Page<CarDto> cars = carService.getAllCars(pageable);

        return ResponseEntity.ok(cars);
    }


    // Get by mark
    @GetMapping("/search/mark")
    public ResponseEntity<Car> getCarByMark(@RequestParam String mark) {
        Optional<Car> car = carService.findByMark(mark);
        return car.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get by module
    @GetMapping("/search/module")
    public ResponseEntity<Car> getCarByModule(@RequestParam String module) {
        Optional<Car> car = carService.findByModule(module);
        return car.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get by plate number
    @GetMapping("/search/plate-number")
    public ResponseEntity<Car> getCarByPlateNumber(@RequestParam String plateNumber) {
        Optional<Car> car = carService.findByPlateNumber(plateNumber);
        return car.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update
    @PutMapping("/{plateNumber}")
    public ResponseEntity<CarDto> updateCar(@PathVariable String plateNumber, @Valid @RequestBody CarDto carDto) {
        CarDto updatedCar = carService.updateCar(plateNumber, carDto);
        return ResponseEntity.ok(updatedCar);
    }

    // Delete car by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok("Car deleted successfully");
    }

}