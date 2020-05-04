package com.vincode.simipa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeritaResponse {

    @SerializedName("records")
    private List<BeritaResult> news;

    public List<BeritaResult> getNews() {
        return news;
    }
}
