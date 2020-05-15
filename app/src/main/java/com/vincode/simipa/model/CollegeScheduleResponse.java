package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollegeScheduleResponse {
    @SerializedName("response_code")
    @Expose
    private int responsCode;
    @SerializedName("records")
    @Expose
    private List<CollegeScheduleResult> records = null;

    public int getResponsCode() {
        return responsCode;
    }

    public void setResponsCode(int responsCode) {
        this.responsCode = responsCode;
    }

    public List<CollegeScheduleResult> getRecords() {return records;}

    public void setRecords(List<CollegeScheduleResult> records) { this.records = records; }
}
