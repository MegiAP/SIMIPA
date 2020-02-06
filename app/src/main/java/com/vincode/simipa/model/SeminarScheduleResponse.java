package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SeminarScheduleResponse {
    @SerializedName("records")
    @Expose
    private List<SeminarScheduleResult> records = null;

    public List<SeminarScheduleResult> getRecords() {
        return records;
    }

    public void setRecords(List<SeminarScheduleResult> records) {
        this.records = records;
    }
}
