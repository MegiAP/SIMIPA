package com.vincode.simipa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileResponse {
    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("User Profile")
    private UserData profile;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public UserData getProfile() {
        return profile;
    }

    public ProfileResponse(boolean error, String message, UserData profile) {
        this.error = error;
        this.message = message;
        this.profile = profile;

    }
}
