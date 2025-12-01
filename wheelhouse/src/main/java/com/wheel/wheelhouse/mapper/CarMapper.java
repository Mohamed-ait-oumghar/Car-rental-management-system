package com.wheel.wheelhouse.mapper;

import com.wheel.wheelhouse.dto.CarDto;
import com.wheel.wheelhouse.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    //Entity --> Dto
    public static CarDto toDto(Car car) {
        if(car == null) return null;

        CarDto carDto = new CarDto();
        carDto.setPlateNumber(car.getPlateNumber());
        carDto.setMark(car.getMark());
        carDto.setModule(car.getModule());
        carDto.setPlaceNumber(car.getPlaceNumber());
        carDto.setAssuranceExpDate(car.getAssuranceExpDate());
        carDto.setPrice(car.getPrice());
        carDto.setDateCirculation(car.getDateCirculation());
        carDto.setFuel(car.getFuel());
        carDto.setCarType(car.getCarType());

        return carDto;
    }

    //Dto --> Entity
    public static Car toEntity(CarDto carDto) {
        if(carDto == null) return null;

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

        return car;
    }
}
