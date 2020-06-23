package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SimipaParentsUpdateTokenResponce {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("total_records")
    @Expose
    private Integer totalRecords;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("records")
    @Expose
    private List<SimipaParentsUpdateTokenRecord> records = null;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SimipaParentsUpdateTokenRecord> getRecords() {
        return records;
    }

    public void setRecords(List<SimipaParentsUpdateTokenRecord> records) {
        this.records = records;
    }


}
