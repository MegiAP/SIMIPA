package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountSeminarResponse {

    @SerializedName("records")
    @Expose
    private List<CountSeminarResult> result = null;

    public List<CountSeminarResult> getResult() {
        return result;
    }

    public void setResult(List<CountSeminarResult> result) {
        this.result = result;
    }
}
