package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SeminarResponse {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("records")
    @Expose
    private List<SeminarResult> result = null;

    public List<SeminarResult> getResult() {
        return result;
    }

    public void setResult(List<SeminarResult> result) {
        this.result = result;
    }
}
