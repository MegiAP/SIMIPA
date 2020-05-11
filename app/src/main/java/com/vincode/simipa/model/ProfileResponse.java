package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileResponse {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("records")
    @Expose
    private List<UserProfile> userProfiles = null;

    public List<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public String getError() {
        return error;
    }
}
