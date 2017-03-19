package com.romao.demo.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.support.ConnectionSource;
import com.romao.demo.model.DatabaseHelper;
import com.romao.demo.model.SettingsDao;
import com.romao.demo.model.UserDao;
import com.romao.demo.network.Api;
import com.romao.demo.network.ApiProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return app;
    }

    @Provides
    @Singleton
    Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }

    @Provides
    @Singleton
    DatabaseHelper provideDatabaseHelper(Context context) {
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        databaseHelper.getWritableDatabase();

        return databaseHelper;
    }

    @Provides
    @Singleton
    ConnectionSource provideConnectionSource(DatabaseHelper databaseHelper) {
        ConnectionSource connectionSource = databaseHelper.getConnectionSource();
        return connectionSource;
    }

    @Provides
    @Singleton
    ApiProvider provideApiProvider(UserDao userDao, SettingsDao settingsDao) {
        return new ApiProvider(userDao, settingsDao);
    }

    @Provides
    @Singleton
    Api provideApi(ApiProvider apiProvider) {
        return apiProvider.getApi();
    }

}
