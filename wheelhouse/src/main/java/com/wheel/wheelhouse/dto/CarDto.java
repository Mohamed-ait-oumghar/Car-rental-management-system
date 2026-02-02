package com.wheel.wheelhouse.dto;

import com.wheel.wheelhouse.tool.CarType;
import com.wheel.wheelhouse.tool.FuelType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private Long carId;

    @NotBlank(message = "Plate number shouldn't be null")
    @Pattern(regexp = "^[A-Z0-9-]{5,15}$", message = "Plate number must be 5-15 alphanumeric characters")
    private String plateNumber;

    @NotBlank(message = "Mark is required")
    private String mark;

    @NotBlank(message = " Module name shouldn't be null")
    private String module;

    @NotNull(message = "Place number date shouldn't be null")
    @Min(value = 1, message = "Place number must be at least 1")
    @Max(value = 8, message = "Place number cannot exceed 20")
    private Integer placeNumber;

    @NotNull(message = "Car assurance expiration date shouldn't be null")
    @FutureOrPresent(message = "Car assurance expiration date must be in the present or future.")
    private LocalDate assuranceExpDate;

    @NotNull(message = "Price per day shouldn't be null")
    @Positive(message = "Price must be positive")
    private Integer price;

    @NotNull(message = "Circulation date shouldn't be null")
    @Past(message = "Circulation date must be in the past")
    private LocalDate dateCirculation;

    @NotNull(message = "Car fuel type shouldn't be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "fuel")
    private FuelType fuel;

    @NotNull(message = "Car type shouldn't be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "carType")
    private CarType carType;

}
