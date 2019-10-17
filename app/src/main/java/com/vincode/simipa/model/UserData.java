package com.vincode.simipa.model;

import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("Id")
    private long id;

    @SerializedName("User Login")
    private String userLogin;

    @SerializedName("Display Name")
    private String displayName;

    public UserData(long id, String userLogin, String displayName) {
        this.id = id;
        this.userLogin = userLogin;
        this.displayName = displayName;
    }

    public UserData(String userLogin, String displayName) {
        this.userLogin = userLogin;
        this.displayName = displayName;
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
}
