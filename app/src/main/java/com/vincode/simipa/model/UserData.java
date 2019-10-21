package com.vincode.simipa.model;

import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("Id")
    private long id;

    @SerializedName("User Login")
    private String userLogin;

    @SerializedName("Display Name")
    private String displayName;

    @SerializedName("Email")
    private String email;

    public UserData(long id, String userLogin, String displayName, String email) {
        this.id = id;
        this.userLogin = userLogin;
        this.displayName = displayName;
        this.email = email;
    }

    public UserData(String userLogin, String displayName, String email) {
        this.userLogin = userLogin;
        this.displayName = displayName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }
}
