package com.movify.service;

import com.movify.dto.*;

public interface AuthService {

    ServiceResponse register(UserCreationRequest request);

    ServiceResponse login(LoginRequest request);

    ServiceResponse forgotPassword(ForgotPasswordRequest request);

    ServiceResponse resetPassword(ResetPasswordRequest request);

}
