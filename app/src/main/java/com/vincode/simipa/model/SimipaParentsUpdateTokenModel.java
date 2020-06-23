package com.vincode.simipa.model;

import com.google.gson.annotations.SerializedName;

public class SimipaParentsUpdateTokenModel {

    @SerializedName("user")
    String user;
    @SerializedName("token")
    String token;
    @SerializedName("imei")
    String imei;

    public SimipaParentsUpdateTokenModel(String user, String token, String imei) {
        this.user = user;
        this.token = token;
        this.imei = imei;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
