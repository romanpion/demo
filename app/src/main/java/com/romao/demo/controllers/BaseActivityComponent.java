package com.romao.demo.controllers;

import com.romao.demo.controllers.userprofile.UserProfileComponent;
import com.romao.demo.controllers.userprofile.UserProfileModule;

import dagger.Subcomponent;


@ForActivity
@Subcomponent(modules = BaseActivityModule.class)
public interface BaseActivityComponent {

    UserProfileComponent userProfileComponent(UserProfileModule userProfileModule);
}
