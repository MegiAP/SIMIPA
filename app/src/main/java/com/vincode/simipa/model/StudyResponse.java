package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudyResponse {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("result")
    @Expose
    private List<StudyResult> result = null;

    public String getValue() {
        return value;
    }


    public List<StudyResult> getResult() {
        return result;
    }
}
