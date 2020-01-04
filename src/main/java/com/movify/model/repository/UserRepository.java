package com.movify.model.repository;

import com.movify.model.User;

public interface UserRepository {

    void save(User user);

    User findById(Long Id);

    User findByEmail(String email);

}
