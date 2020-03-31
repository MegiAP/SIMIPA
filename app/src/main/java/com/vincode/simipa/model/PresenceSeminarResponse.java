package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PresenceSeminarResponse {

    @SerializedName("records")
    @Expose
    private List<PresenceSeminarResult> records = null;

    public List<PresenceSeminarResult> getRecords() {
        return records;
    }

    public void setRecords(List<PresenceSeminarResult> records) {
        this.records = records;
    }
}
