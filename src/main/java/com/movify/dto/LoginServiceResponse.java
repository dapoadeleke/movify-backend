package com.movify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginServiceResponse {
    String token;
    Long id;
    String fullName;
    String email;
}
