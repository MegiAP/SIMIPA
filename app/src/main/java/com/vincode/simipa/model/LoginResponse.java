package com.vincode.simipa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("records")
    private List<UserData> user;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<UserData> getUser() {
        return user;
    }

    public LoginResponse(boolean error, String message, List<UserData> user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }
}
