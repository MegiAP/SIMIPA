package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClassScheduleResponse {

        @SerializedName("records")
        @Expose
        private List<ClassScheduleResult> records = null;

        public List<ClassScheduleResult> getRecords() {
            return records;
        }

        public void setRecords(List<ClassScheduleResult> records) {
            this.records = records;
        }

}
