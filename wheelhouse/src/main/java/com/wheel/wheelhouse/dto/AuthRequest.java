package com.wheel.wheelhouse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // Required by JSON deserialization (e.g., Spring's @RequestBody)
@AllArgsConstructor // REQUIRED for the test initialization: new AuthRequest(email, password)
public class AuthRequest {

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String userName;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "RoleName cannot be blank")
    private String RoleName;

}