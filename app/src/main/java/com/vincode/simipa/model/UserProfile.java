package com.vincode.simipa.model;

import com.google.gson.annotations.SerializedName;

public class UserProfile {

    @SerializedName("Id")
    private long id;

    @SerializedName("User Login")
    private String userLogin;

    @SerializedName("Display Name")
    private String displayName;

    @SerializedName("Email")
    private String email;

    public UserProfile(long id, String userLogin, String displayName) {
        this.id = id;
        this.userLogin = userLogin;
        this.displayName = displayName;
    }

    public UserProfile(String userLogin, String displayName) {
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
