package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PracticeScheduleResponse {
    @SerializedName("records")
    @Expose
    private List<PracticeScheduleResult> records = null;

    public List<PracticeScheduleResult> getRecords() {return records;}

    public void setRecords(List<PracticeScheduleResult> records) { this.records = records; }
}
