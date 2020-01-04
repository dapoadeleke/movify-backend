package com.movify.controller;

import com.movify.dto.*;
import com.movify.service.AuthService;
import com.movify.utils.Utility;
import org.jooby.mvc.Body;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 *
 * Everything about Authentication.
 */
@Path("/api/v1/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Inject
    AuthService authService;

    @Inject
    Utility utility;

    /**
     * Login API
     * @param req
     * @return LoginServiceResponse
     */
    @POST
    @Path("/login")
    public ServiceResponse login(@Body LoginRequest req) {
        ServiceResponse res = new ServiceResponse();
        this.utility.validateRequest(req);
        return this.authService.login(req);
    }

    /**
     * Register API
     * @param req
     * @return
     */
    @POST
    @Path("/register")
    public ServiceResponse register(@Body UserCreationRequest req) {
        ServiceResponse res = new ServiceResponse();
        this.utility.validateRequest(req);
        return this.authService.register(req);
    }

    @POST
    @Path("/forgot-password")
    public ServiceResponse forgotPassword(@Body ForgotPasswordRequest req) {
        ServiceResponse res = new ServiceResponse();
        this.utility.validateRequest(req);
        return this.authService.forgotPassword(req);
    }

    @POST
    @Path("/reset-password")
    public ServiceResponse resetPassword(@Body ResetPasswordRequest req) {
        ServiceResponse res = new ServiceResponse();
        this.utility.validateRequest(req);
        return this.authService.resetPassword(req);
    }

}
