package com.vincode.simipa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileResponse {
    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("Email")
    private UserData email;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public UserData getEmail() {
        return email;
    }

    public ProfileResponse(boolean error, String message, UserData email) {
        this.error = error;
        this.message = message;
        this.email = email;

    }
}
