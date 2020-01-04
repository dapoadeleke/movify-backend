package com.movify.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserCreationRequest {
    @NotBlank(message = "Full name is required")
    String fullName;
    @Email(message = "Invalid Email")
    String email;
    @NotBlank(message = "Password is required")
    String password;
}


