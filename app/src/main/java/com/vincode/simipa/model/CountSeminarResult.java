package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountSeminarResult {

    @SerializedName("Jumlah Seminar KP")
    @Expose
    private String jumlahSemKP;
    @SerializedName("Jumlah Seminar Usul")
    @Expose
    private String jumlahSemUsul;
    @SerializedName("Jumlah Seminar Hasil")
    @Expose
    private String jumlahSemHas;

    public String getJumlahSemKP() {
        return jumlahSemKP;
    }

    public void setJumlahSemKP(String jumlahSemKP) {
        this.jumlahSemKP = jumlahSemKP;
    }

    public String getJumlahSemUsul() {
        return jumlahSemUsul;
    }

    public void setJumlahSemUsul(String jumlahSemUsul) {
        this.jumlahSemUsul = jumlahSemUsul;
    }

    public String getJumlahSemHas() {
        return jumlahSemHas;
    }

    public void setJumlahSemHas(String jumlahSemHas) {
        this.jumlahSemHas = jumlahSemHas;
    }
}
