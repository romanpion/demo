package com.romao.demo.controllers.userprofile;

import dagger.Subcomponent;

@ForUserProfile
@Subcomponent(modules = UserProfileModule.class)
public interface UserProfileComponent {

    UserProfilePresenter userProfilePresenter();
}
