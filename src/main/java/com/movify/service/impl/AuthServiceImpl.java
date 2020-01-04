package com.movify.service.impl;

import com.movify.dto.*;
import com.movify.enums.EmailMessages;
import com.movify.enums.Message;
import com.movify.model.User;
import com.movify.model.repository.UserRepository;
import com.movify.security.Hash;
import com.movify.service.AuthService;
import com.movify.service.EmailService;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class AuthServiceImpl implements AuthService {

    @Inject
    UserRepository userRepository;

    @Inject
    EmailService emailService;

    @Override
    public ServiceResponse register(UserCreationRequest request) {
        ServiceResponse response = new ServiceResponse(Message.ERROR, Message.GENERAL_ERROR_MESSAGE);
        /* Ensure email is unique */
        User emailUser = this.userRepository.findByEmail(request.getEmail());
        if (emailUser != null) {
            return response.setMessage(Message.EMAIL_EXIST);
        }
        User user = new User();
        try {
            user = this.generateUserFromRequest(request);
        } catch (Exception e) {e.printStackTrace();}
        this.userRepository.save(user);
        // Todo send out registration successful email notification
        this.emailService.sendEmail(user.getEmail(), user.getFullName(), "Welcome to Movify", EmailMessages.REGISTRATION_SUCCESSFUL_EMAIL);
        return response.setCode(Message.SUCCESS).setMessage(Message.GENERAL_SUCCESS_MESSAGE).setData(this.generateUserDtoFromUser(user));
    }

    @Override
    public ServiceResponse login(LoginRequest request) {
        return null;
    }

    @Override
    public ServiceResponse forgotPassword(ForgotPasswordRequest request) {
        return null;
    }

    @Override
    public ServiceResponse resetPassword(ResetPasswordRequest request) {
        return null;
    }

    private User generateUserFromRequest(UserCreationRequest request) {
        User user = new User();
        user.setDateCreated(LocalDateTime.now());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        String passwordHash = Hash.hashPassword(request.getPassword());
        user.setPasswordHash(passwordHash);
        return user;
    }

    private UserDTO generateUserDtoFromUser(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        return dto;
    }

}
