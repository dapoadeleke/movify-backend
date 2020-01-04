package com.movify.model.repository.impl;

import com.movify.model.User;
import com.movify.model.repository.UserRepository;
import io.ebean.EbeanServer;

import javax.inject.Inject;

public class UserRepositoryImpl implements UserRepository {

    @Inject
    EbeanServer store;

    @Override
    public void save(User user) {
        this.store.save(user);
    }

    @Override
    public User findById(Long Id) {
        return this.store.find(User.class).where().idEq(Id).findOne();
    }

    @Override
    public User findByEmail(String email) {
        return this.store.find(User.class).where().eq("email", email).findOne();
    }
}
