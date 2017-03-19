package com.romao.demo.application;

import android.content.SharedPreferences;

import com.j256.ormlite.support.ConnectionSource;
import com.romao.demo.model.CompanyDao;
import com.romao.demo.model.LocationDao;
import com.romao.demo.model.SettingsDao;
import com.romao.demo.model.UserDao;

import java.sql.SQLException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class DaoModule {

    @Singleton
    @Provides
    UserDao provideUserDao(ConnectionSource connectionSource, SharedPreferences sharedPreferences, CompanyDao companyDao) {
        try {
            return new UserDao(connectionSource, sharedPreferences, companyDao);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Provides
    @Singleton
    SettingsDao provideSettingsDao(SharedPreferences sharedPreferences) {
        return new SettingsDao(sharedPreferences);
    }

    @Provides
    @Singleton
    LocationDao provideLocationDao(ConnectionSource connectionSource) {
        try {
            return new LocationDao(connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Provides
    @Singleton
    CompanyDao provideCompanyDao(ConnectionSource connectionSource) {
        try {
            return new CompanyDao(connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}