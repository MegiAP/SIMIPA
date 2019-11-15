package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollegeScheduleResponse {
    @SerializedName("records")
    @Expose
    private List<CollegeScheduleResult> records = null;

    public List<CollegeScheduleResult> getRecords() {return records;}

    public void setRecords(List<CollegeScheduleResult> records) { this.records = records; }
}
