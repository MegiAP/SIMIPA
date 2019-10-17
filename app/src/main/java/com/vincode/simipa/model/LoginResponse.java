package com.vincode.simipa.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("User Data")
    private UserData user;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public UserData getUser() {
        return user;
    }

    public LoginResponse(boolean error, String message, UserData user) {
        this.error = error;
        this.message = message;
        this.user = user;

    }
}
