package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SimipaParentsListParentResponce {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("total_records")
    @Expose
    private Integer totalRecords;
    @SerializedName("records")
    @Expose
    private List<SimipaParentsListParentRecord> records = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<SimipaParentsListParentRecord> getRecords() {
        return records;
    }

    public void setRecords(List<SimipaParentsListParentRecord> records) {
        this.records = records;
    }

}
