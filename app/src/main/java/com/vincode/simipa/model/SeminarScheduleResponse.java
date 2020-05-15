package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SeminarScheduleResponse {
    @SerializedName("response_code")
    @Expose
    private int responsCode;
    @SerializedName("records")
    @Expose
    private List<SeminarScheduleResult> records = null;

    public int getResponsCode() {
        return responsCode;
    }

    public void setResponsCode(int responsCode) {
        this.responsCode = responsCode;
    }

    public List<SeminarScheduleResult> getRecords() {
        return records;
    }

    public void setRecords(List<SeminarScheduleResult> records) {
        this.records = records;
    }
}
