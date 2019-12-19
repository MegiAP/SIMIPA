package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KRSResponse {

    @SerializedName("records")
    @Expose
    private List<KRSResult> records = null;

    public List<KRSResult> getRecords() {
        return records;
    }

    public void setRecords(List<KRSResult> records) {
        this.records = records;
    }
}
