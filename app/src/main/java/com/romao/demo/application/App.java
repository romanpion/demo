package com.romao.demo.application;

import android.app.Application;

import com.romao.demo.BuildConfig;

import timber.log.Timber;


public class App extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .applicationModule(getApplicationModule())
                .build();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private ApplicationModule getApplicationModule() {

        return new ApplicationModule((App) getApplicationContext());
    }
}
