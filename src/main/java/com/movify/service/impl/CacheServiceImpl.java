package com.movify.service.impl;

import com.google.common.cache.Cache;
import com.movify.model.User;
import com.movify.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class CacheServiceImpl implements CacheService {

    private static Logger LOGGER = LoggerFactory.getLogger(CacheServiceImpl.class);

    @Inject
    Cache cache;

    @Override
    public User getUser(String token) {
        return (User) this.cache.getIfPresent(token);
    }

    @Override
    public void storeUser(User user, String token) {
        this.cache.put("user", token);
        this.cache.put(token, user);
    }

    @Override
    public User getUser() {
        try {
            String token = this.cache.getIfPresent("user").toString();
            return (User) this.cache.getIfPresent(token);
        } catch (NullPointerException ex) {
            LOGGER.info("UserSession token not present");
            return null;
        }
    }

    @Override
    public void deleteUser() {
        String token = this.cache.getIfPresent("user").toString();
        this.cache.invalidate("user");
        this.cache.invalidate(token);
    }
}
