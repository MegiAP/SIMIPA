package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SplashResponse {


    @SerializedName("records")
    @Expose
    private List<SplashResult> records = null;

    public List<SplashResult> getRecords() {
        return records;
    }

}
