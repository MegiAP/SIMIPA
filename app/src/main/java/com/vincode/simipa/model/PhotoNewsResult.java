package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoNewsResult {

    @SerializedName("Foto")
    @Expose
    private String foto;

    public String getFoto() {
        return foto;
    }
}
