package com.vincode.simipa.network;

import com.vincode.simipa.model.LoginResponse;
import com.vincode.simipa.model.ProfileResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> userLogin(
            @Field("user") String username,
            @Field("pass") String password
    );

    @FormUrlEncoded
    @POST("read-profile.php")
    Call<ProfileResponse> getUserData(
            @Field("Email") String email
    );
}
