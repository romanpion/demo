package com.romao.demo.model;

import android.content.SharedPreferences;

import com.romao.demo.BuildConfig;

public class SettingsDao {
    private SharedPreferences sharedPreferences;

    private static final String BASE_URL_DEBUG = "http://otivrapidev.azurewebsites.net/";
    private static final String BASE_URL_LIVE = "http://otivrapi.azurewebsites.net";

    public SettingsDao(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public String getBaseUrl() {
        return BASE_URL_DEBUG;
    }

    public boolean isDebug() {
        boolean isDebug = BuildConfig.DEBUG;

        return isDebug;
    }
}
