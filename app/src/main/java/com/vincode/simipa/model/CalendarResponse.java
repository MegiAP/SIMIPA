package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CalendarResponse {

    @SerializedName("records")
    @Expose
    private List<CalendarResult> records = null;

    public List<CalendarResult> getRecords() {
        return records;
    }

    public void setRecords(List<CalendarResult> records) {
        this.records = records;
    }
}
