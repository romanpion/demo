package com.romao.demo.controllers.userprofile;

import com.romao.demo.domain.PasswordInteractor;
import com.romao.demo.domain.UsersInteractor;

import dagger.Module;
import dagger.Provides;


@Module
public class UserProfileModule {

    private UserProfileView view;

    public UserProfileModule(UserProfileView view) {
        this.view = view;
    }

    @ForUserProfile
    @Provides
    public UserProfilePresenter provideUserProfilePresenter(UsersInteractor usersInteractor, PasswordInteractor passwordInteractor) {
        return new UserProfilePresenter(view, usersInteractor, passwordInteractor);
    }
}
