package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SeminarPresenceResponse {

    @SerializedName("result")
    @Expose
    private List<SeminarPresenceResult> result = null;

    public List<SeminarPresenceResult> getResult() {
        return result;
    }

    public void setResult(List<SeminarPresenceResult> result) {
        this.result = result;
    }
}
