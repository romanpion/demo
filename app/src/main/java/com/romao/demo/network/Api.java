package com.romao.demo.network;

import com.romao.demo.network.entities.ChangePasswordRequest;
import com.romao.demo.network.entities.UserRequest;
import com.romao.demo.network.entities.UserResponse;
import com.romao.demo.network.entities.UserUpdateRequest;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface Api {

    @GET("api/registration/sendlink/{userid}")
    Observable<Response<ResponseBody>> sendRegistrationLink(@Path("userid") int userId);

    @POST("api/user/search")
    Observable<List<UserResponse>> getUsers(@Body UserRequest userRequest);

    @POST("api/user/update")
    Observable<Void> updateUser(@Body UserUpdateRequest request);

    @GET("api/user/{userId}")
    Observable<UserResponse> getUser(@Path("userId") int userId);

    @POST("api/password/change")
    Observable<Void> changePassword(@Body ChangePasswordRequest request);

}
