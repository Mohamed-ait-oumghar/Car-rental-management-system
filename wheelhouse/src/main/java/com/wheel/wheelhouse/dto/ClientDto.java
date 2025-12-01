package com.wheel.wheelhouse.dto;

import com.wheel.wheelhouse.entity.Order;
import com.wheel.wheelhouse.tool.GenderType;
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
public class ClientDto {

    @NotBlank(message = "Client first name shouldn't be null")
    private String clientFirstName;
    @NotBlank(message = "Client last name shouldn't be null")
    private String clientLastName;

    @NotBlank(message = "Client last name shouldn't be null")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Cin is required")
    @Size(min = 5, max = 20, message = "CIN must be between 5 and 20 characters")
    private String cin;

    @NotNull(message = "Client Date of birth shouldn't be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender shouldn't be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderType gender;

    @NotBlank(message = "Client city shouldn't be null")
    private String clientCity;

    @NotBlank(message = "Client country shouldn't be null")
    private String clientCountry;

}
