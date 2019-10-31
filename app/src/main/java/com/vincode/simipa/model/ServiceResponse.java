package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceResponse {

    @SerializedName("records")
    @Expose
    private List<ServiceResult> services = null;

    public List<ServiceResult> getServices() {
        return services;
    }

    public void setServices(List<ServiceResult> services) {
        this.services = services;
    }
}
