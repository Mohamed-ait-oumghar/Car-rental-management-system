package com.wheel.wheelhouse.service;

import com.wheel.wheelhouse.customException.CarDeletionException;
import com.wheel.wheelhouse.dto.CarDto;
import com.wheel.wheelhouse.entity.Car;
import com.wheel.wheelhouse.mapper.CarMapper;
import com.wheel.wheelhouse.repository.CarRepository;
import com.wheel.wheelhouse.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CarService {

    CarRepository carRepository;

    OrderRepository orderRepository;

    public CarService(CarRepository carRepository, OrderRepository orderRepository) {
        this.carRepository = carRepository;
        this.orderRepository = orderRepository;
    }

    //Create new car
    public Car createCar(CarDto carDto) {

        Car car = new Car();

        car.setPlateNumber(carDto.getPlateNumber());
        car.setMark(carDto.getMark());
        car.setModule(carDto.getModule());
        car.setPlaceNumber(carDto.getPlaceNumber());
        car.setAssuranceExpDate(carDto.getAssuranceExpDate());
        car.setPrice(carDto.getPrice());
        car.setDateCirculation(carDto.getDateCirculation());
        car.setFuel(carDto.getFuel());
        car.setCarType(carDto.getCarType());

        return carRepository.save(car);
    }
    //Get mark
    public Optional<Car> findByMark(String mark) {
        return carRepository.findByMark(mark);
    }

    //Get module
    public Optional<Car> findByModule(String module) {
        return carRepository.findByModule(module);
    }
        //Get module
    public Optional<Car> findByPlateNumber(String plateNumber) {
        return carRepository.findByPlateNumber(plateNumber);
    }

    //Update Car
    public CarDto updateCar(String plateNumber, CarDto updateCarDto) {

        Car existingCar = carRepository.findByPlateNumber(plateNumber)
                .orElseThrow(() -> new RuntimeException("The plate under the number" + plateNumber + "Not found"));

        if (updateCarDto.getMark() != null) {
            existingCar.setMark(updateCarDto.getMark());
        }
        if (updateCarDto.getModule() != null) {
            existingCar.setModule(updateCarDto.getModule());
        }
        if (updateCarDto.getPlaceNumber() != null) {
            existingCar.setPlaceNumber(updateCarDto.getPlaceNumber());
        }
        if (updateCarDto.getPrice() != null) {
            existingCar.setPrice(updateCarDto.getPrice());
        }

        Car saved = carRepository.save(existingCar);

        return CarMapper.toDto(saved);

    }

    //Delete
    public void deleteCar(Long id) {

        if (!carRepository.existsById(id)) {
            throw new RuntimeException("Car not found with id: " + id);
        }

        if (orderRepository.existsByCar_CarId(id)) {
            throw new CarDeletionException(
                    "Cannot delete car with id " + id + " because it is assigned to one or more orders."
            );
        }
        carRepository.deleteById(id);
    }
    
}
