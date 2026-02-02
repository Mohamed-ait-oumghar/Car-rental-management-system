package com.wheel.wheelhouse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "User name shouldn't be null")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName;

    @NotBlank
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password shouldn't be null")
    @Size(min = 6, message = "Password must be at least 6 characters")
    /*
    the annotated property will only be used during deserialization (reading a JSON into a Java object)
    and will be ignored during serialization (writing a Java object to JSON)
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotEmpty(message = "User must have at least one role")
    private Set<String> rolesName = new HashSet<>();

}
