package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SplashResult {

    @SerializedName("vector")
    @Expose
    private String vector;

    public String getVector() {
        return vector;
    }

    public void setVector(String vector) {
        this.vector = vector;
    }
}
