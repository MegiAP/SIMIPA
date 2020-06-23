package com.vincode.simipa.model;

import com.google.gson.annotations.SerializedName;

public class SimipaParentsRejectParentModel {

    @SerializedName("id")
    String id;
    @SerializedName("user")
    String user;

    public SimipaParentsRejectParentModel(String id, String user) {
        this.id = id;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
