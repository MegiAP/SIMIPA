package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("kuota")
    @Expose
    private String kuota;

    public String getKuota() {
        return kuota;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

}
