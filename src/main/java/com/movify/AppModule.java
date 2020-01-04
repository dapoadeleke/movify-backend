package com.movify;

import com.google.inject.Binder;
import com.movify.model.repository.UserRepository;
import com.movify.model.repository.UserRepositoryImpl;
import com.movify.service.AuthService;
import com.movify.service.CacheService;
import com.movify.service.EmailService;
import com.movify.service.UserService;
import com.movify.service.impl.AuthServiceImpl;
import com.movify.service.impl.CacheServiceImpl;
import com.movify.service.impl.EmailServiceImpl;
import com.movify.service.impl.UserServiceImpl;
import com.typesafe.config.Config;
import org.jooby.Env;
import org.jooby.Jooby;

public class AppModule implements Jooby.Module {
    @Override
    public void configure(Env env, Config conf, Binder binder) throws Throwable {

        // Repositories
        binder.bind(UserRepository.class).to(UserRepositoryImpl.class);

        // Services
        binder.bind(AuthService.class).to(AuthServiceImpl.class);
        binder.bind(UserService.class).to(UserServiceImpl.class);
        binder.bind(EmailService.class).to(EmailServiceImpl.class);
        binder.bind(CacheService.class).to(CacheServiceImpl.class);

    }
}
