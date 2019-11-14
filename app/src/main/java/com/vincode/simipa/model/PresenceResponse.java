package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PresenceResponse {

    @SerializedName("records")
    @Expose
    private List<PresenceResult> presenceResults = null;

    public List<PresenceResult> getRecords() {
        return presenceResults;
    }

    public void setRecords(List<PresenceResult> presenceResults) {
        this.presenceResults = presenceResults;
    }
}
