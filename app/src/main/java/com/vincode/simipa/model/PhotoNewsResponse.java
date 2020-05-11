package com.vincode.simipa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoNewsResponse {

    @SerializedName("records")
    private List<PhotoNewsResult> photos;

    public List<PhotoNewsResult> getPhotoResponse() {
        return photos;
    }
}
