package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScholarshipListResponse {

    @SerializedName("records")
    @Expose
    private List<ScholarshipListResult> records = null;

    public List<ScholarshipListResult> getRecords() {
        return records;
    }

    public void setRecords(List<ScholarshipListResult> records) {
        this.records = records;
    }
}
