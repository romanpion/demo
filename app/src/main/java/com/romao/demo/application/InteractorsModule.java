package com.romao.demo.application;

import com.romao.demo.domain.PasswordInteractor;
import com.romao.demo.domain.UsersInteractor;
import com.romao.demo.model.UserDao;
import com.romao.demo.network.Api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class InteractorsModule {

    @Provides
    @Singleton
    UsersInteractor provideUsersInteractor(Api api, UserDao userDao) {

        return new UsersInteractor(api, userDao);
    }

    @Provides
    @Singleton
    PasswordInteractor providePasswordInteractor(Api api) {

        return new PasswordInteractor(api);
    }
}
