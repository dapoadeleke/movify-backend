package com.movify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email")
    String email;
    @NotBlank(message = "Password is required")
    String password;
}
