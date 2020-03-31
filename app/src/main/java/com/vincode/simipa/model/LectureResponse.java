package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LectureResponse {
    @SerializedName("records")
    @Expose
    private List<LectureResult> records = null;

    public List<LectureResult> getRecords() {
        return records;
    }

    public void setRecords(List<LectureResult> records) {
        this.records = records;
    }
}
