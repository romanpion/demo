package com.romao.demo.controllers;

import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseActivityModule {

    private final BaseActivity activity;

    public BaseActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ForActivity
    BaseActivity provideBaseActivity() {
        return this.activity;
    }

    @ForActivity
    @Provides
    LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(activity);
    }
}
