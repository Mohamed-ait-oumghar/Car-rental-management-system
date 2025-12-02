package com.wheel.wheelhouse.dto;

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
public class OrderDto {



    @NotNull(message = "Debut date shouldn't be null")
    @FutureOrPresent(message = "Debut date must be in the present or future.")
    private LocalDate debutDate;

    @NotNull(message = "End date shouldn't be null")
    @AssertTrue(message = "End date must be after start date")
    public boolean isValidDates() {
        if (debutDate == null || endDate == null) return true;
        return endDate.isAfter(debutDate);
    }
    private LocalDate endDate;

    @NotBlank(message = "Debut location shouldn't be null")
    private String debutLocation;

    @NotBlank(message = "Fin location shouldn't be null")
    private String finLocation;

    @NotNull(message = "Total price shouldn't be null")
    @Positive(message = "Price must be positive, Zero is considered as invalid value!")
    private Integer totalPrice;

    @FutureOrPresent(message = "Creation date must be today or in the future")
    private LocalDate creationDate;


    private Long clientId;

    @NotNull(message = "Car id is required")
    private Long carId;

    @NotNull(message = "User id is required")
    private Long userId;
}