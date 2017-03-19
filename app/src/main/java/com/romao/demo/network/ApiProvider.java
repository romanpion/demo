package com.romao.demo.network;

import android.text.TextUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaMapper;
import com.romao.demo.model.SettingsDao;
import com.romao.demo.model.UserDao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiProvider {
    private static final int CONNECTIONS_COUNT = 5;
    private static final long KEEP_ALIVE_DURATION = 300000;
    private static final long TIMEOUT_MINUTES = 2;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String AUTH_REQUEST_HEADER = "Authorization";
    public static final String CONTENT_TYPE_HEADER = "Content-Type";
    public static final String CONTENT_TYPE_VALUE = "application/json; charset=utf-8";

    public static final String ACCEPT_HEADER = "Accept";
    public static final String ACCEPT_VALUE = "application/json";

    public static final String CACHE_CONTROL_HEADER = "Cache-Control";
    public static final String CACHE_CONTROL_VALUE = "no-store, no-cache";
    public static final int CODE_UNAUTHORIZED = 401;

    private Api api;
    private OkHttpClient client;
    private UserDao userDao;
    private SettingsDao settingsDao;

    public ApiProvider(UserDao userDao, SettingsDao settingsDao) {
        this.userDao = userDao;
        this.settingsDao = settingsDao;

        setupProvider();
    }

    public void setupProvider() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .addInterceptor(getAuthInterceptor(userDao))
                .addInterceptor(getHostSelectionInterceptor())
                .readTimeout(TIMEOUT_MINUTES, TimeUnit.MINUTES)
                .connectTimeout(TIMEOUT_MINUTES, TimeUnit.MINUTES)
                .connectionPool(new ConnectionPool(CONNECTIONS_COUNT, KEEP_ALIVE_DURATION, TimeUnit.MILLISECONDS));


        if (settingsDao.isDebug()) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(loggingInterceptor);
        }

        client = clientBuilder.build();

        ObjectMapper mapper = new JodaMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        mapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Converter.Factory converterFactory = JacksonConverterFactory.create(mapper);

        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .baseUrl(settingsDao.getBaseUrl())
                .client(client);

        api = builder.build().create(Api.class);
    }

    public Api getApi() {

        return api;
    }

    private AuthInterceptor getAuthInterceptor(UserDao userDao) {
        return new AuthInterceptor(userDao);
    }

    public Interceptor getHostSelectionInterceptor() {
        return new HostSelectionInterceptor();
    }

    public static class AuthInterceptor implements Interceptor {

        private UserDao userDao;

        public AuthInterceptor(UserDao userDao) {

            this.userDao = userDao;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request newReq;
            if (TextUtils.isEmpty(userDao.getAuthToken())
                    || !TextUtils.isEmpty(request.header(AUTH_REQUEST_HEADER))) {
                newReq = request;
            } else {
                String token = userDao.getAuthToken();
                newReq = request.newBuilder()
                        .addHeader(AUTH_REQUEST_HEADER, TOKEN_PREFIX + token)
                        .build();
            }

            return chain.proceed(newReq.newBuilder()
                    .addHeader(CACHE_CONTROL_HEADER, CACHE_CONTROL_VALUE)
                    .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_VALUE)
                    .addHeader(ACCEPT_HEADER, ACCEPT_VALUE)
                    .build());
        }
    }

    public final class HostSelectionInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            HttpUrl baseUrl = HttpUrl.parse(settingsDao.getBaseUrl());

            HttpUrl newUrl = request.url().newBuilder()
                    .host(baseUrl.host())
                    .scheme(baseUrl.scheme())
                    .port(baseUrl.port())
                    .build();

            request = request.newBuilder()
                    .url(newUrl)
                    .build();

            return chain.proceed(request);
        }
    }
}
