package com.movify.service;

import com.movify.model.User;

public interface CacheService {

    User getUser(String token);

    void storeUser(User user, String token);

    User getUser();

    void deleteUser();

}
