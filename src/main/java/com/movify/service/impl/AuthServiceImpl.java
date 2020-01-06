package com.movify.service.impl;

import com.movify.dto.*;
import com.movify.enums.EmailMessages;
import com.movify.enums.Message;
import com.movify.enums.Status;
import com.movify.model.User;
import com.movify.model.repository.UserRepository;
import com.movify.security.Hash;
import com.movify.security.SecurityConstants;
import com.movify.service.AuthService;
import com.movify.service.CacheService;
import com.movify.service.EmailService;
import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.UUID;

public class AuthServiceImpl implements AuthService {

    final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Inject
    Config config;

    @Inject
    UserRepository userRepository;

    @Inject
    EmailService emailService;

    @Inject
    CacheService cacheService;

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
        ServiceResponse res = new ServiceResponse();
        res.setCode(Message.ERROR);
        res.setMessage(Message.GENERAL_ERROR_MESSAGE);
        User user = userRepository.findByEmail(request.getEmail());
        if(user == null){
            LOGGER.error(String.format("Login failed. User %s does not exist", request.getEmail()));
            res.setMessage(Message.USER_DOES_NOT_EXIST_MESSAGE);
            return res;
        }

        if(user.getFailedLoginCount() > this.config.getInt("system.login.count.max")){
            user.setStatus(Status.BLOCKED);
            res.setMessage(Message.USER_ACCOUNT_BLOCKED_MESSAGE);
            return res;
        }

        if(!Hash.checkPassword(request.getPassword(), user.getPasswordHash())){
            int failedLoginCount = user.getFailedLoginCount() + 1;
            user.setFailedLoginCount(failedLoginCount);
            userRepository.save(user);
            res.setMessage(Message.INVALID_PASSWORD);
            return res;
        }

        user.setFailedLoginCount(0);
        user.setLastLoggedIn(LocalDateTime.now());

        LoginServiceResponse loginResponse = new LoginServiceResponse();
        String token = SecurityConstants.TOKEN_PREFIX.concat(UUID.randomUUID().toString());
        loginResponse.setId(user.getId());
        loginResponse.setToken(token);
        loginResponse.setFullName(user.getFullName());
        loginResponse.setEmail(user.getEmail());
        userRepository.save(user);
        this.cacheService.storeUser(user, token);
        res.setData(loginResponse);

        return res.setCode(Message.SUCCESS).setMessage(Message.GENERAL_SUCCESS_MESSAGE);
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
