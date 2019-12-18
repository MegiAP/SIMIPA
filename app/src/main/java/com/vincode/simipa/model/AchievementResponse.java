package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AchievementResponse {

    @SerializedName("records")
    @Expose
    private List<AchievementResult> records = null;

    public List<AchievementResult> getRecords() {
        return records;
    }

    public void setRecords(List<AchievementResult> records) {
        this.records = records;
    }
}
