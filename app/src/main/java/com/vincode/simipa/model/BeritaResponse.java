package com.vincode.simipa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeritaResponse {

    @SerializedName("results")
    private List<BeritaResult> movies;

    public List<BeritaResult> getMovies() {
        return movies;
    }
}
