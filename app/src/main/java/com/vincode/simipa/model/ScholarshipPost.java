package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScholarshipPost {
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

}
