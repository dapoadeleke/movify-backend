package com.movify.service;

import com.movify.dto.ServiceResponse;
import com.movify.dto.UserCreationRequest;

public interface UserService {

    ServiceResponse find(Long id);

    ServiceResponse save(UserCreationRequest request);

}
