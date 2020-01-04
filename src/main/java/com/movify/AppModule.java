package com.movify;

import com.google.inject.Binder;
import com.movify.model.repository.MovieRepository;
import com.movify.model.repository.UserRepository;
import com.movify.model.repository.impl.MovieRepositoryImpl;
import com.movify.model.repository.impl.UserRepositoryImpl;
import com.movify.service.*;
import com.movify.service.impl.*;
import com.typesafe.config.Config;
import org.jooby.Env;
import org.jooby.Jooby;

public class AppModule implements Jooby.Module {
    @Override
    public void configure(Env env, Config conf, Binder binder) throws Throwable {

        // Repositories
        binder.bind(UserRepository.class).to(UserRepositoryImpl.class);
        binder.bind(MovieRepository.class).to(MovieRepositoryImpl.class);

        // Services
        binder.bind(AuthService.class).to(AuthServiceImpl.class);
        binder.bind(UserService.class).to(UserServiceImpl.class);
        binder.bind(EmailService.class).to(EmailServiceImpl.class);
        binder.bind(CacheService.class).to(CacheServiceImpl.class);
        binder.bind(MovieService.class).to(MovieServiceImpl.class);


    }
}
