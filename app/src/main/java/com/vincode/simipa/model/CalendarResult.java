package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CalendarResult {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("Tahun_Akademik")
    @Expose
    private String tahunAkademik;

    @SerializedName("Semester")
    @Expose
    private String semester;

    @SerializedName("Kegiatan")
    @Expose
    private String kegiatan;

    @SerializedName("Date")
    @Expose
    private String date;

    @SerializedName("Due_Date")
    @Expose
    private String dueDate;

    @SerializedName("Keterangan")
    @Expose
    private String keterangan;

    @SerializedName("Status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTahunAkademik() {
        return tahunAkademik;
    }

    public void setTahunAkademik(String tahunAkademik) {
        this.tahunAkademik = tahunAkademik;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
