package com.romao.demo.application;

import com.romao.demo.controllers.BaseActivityComponent;
import com.romao.demo.controllers.BaseActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, InteractorsModule.class, DaoModule.class})
public interface AppComponent {

    BaseActivityComponent baseActivityComponent(BaseActivityModule baseActivityModule);
}
