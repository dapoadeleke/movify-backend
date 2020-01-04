package com.movify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResetPasswordRequest {
    Long id;
    @NotBlank(message = "Current password is required")
    String currentPassword;
    @NotBlank(message = "New password is required")
    String newPassword;
    @NotBlank(message = "Confirm new password is required")
    String confirmNewPassword;
}
