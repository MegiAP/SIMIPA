package com.vincode.simipa.network;

import android.widget.TextView;

import com.vincode.simipa.model.LoginResponse;
import com.vincode.simipa.model.ProfileResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> userLogin(
            @Field("user") String username,
            @Field("pass") String password
    );

    @GET("read-profile.php")
    Call<ProfileResponse> userProfile(
            @Query("npm") String npm
    );
}
