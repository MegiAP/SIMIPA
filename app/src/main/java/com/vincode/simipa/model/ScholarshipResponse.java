package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScholarshipResponse {

    @SerializedName("records")
    @Expose
    private List<ScholarshipResult> records = null;

    public List<ScholarshipResult> getRecords() {
        return records;
    }

    public void setRecords(List<ScholarshipResult> records) {
        this.records = records;
    }
}
