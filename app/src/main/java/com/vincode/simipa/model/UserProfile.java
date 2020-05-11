package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile {

    @SerializedName("Npm")
    @Expose
    private String npm;

    @SerializedName("Display_Name")
    @Expose
    private String displayName;

    @SerializedName("Email")
    @Expose
    private String email;

    @SerializedName("Jenis_Kelamin")
    @Expose
    private String jenisKelamin;

    @SerializedName("No_Ponsel")
    @Expose
    private String noPonsel;

    @SerializedName("Agama")
    @Expose
    private String agama;

    @SerializedName("Tempat_Lahir")
    @Expose
    private String tempatLahir;

    @SerializedName("Tanggal_Lahir")
    @Expose
    private String tanggalLahir;

    @SerializedName("Foto")
    @Expose
    private String foto;

    @SerializedName("Jurusan")
    @Expose
    private String jurusan;

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getNoPonsel() {
        return noPonsel;
    }

    public String getAgama() {
        return agama;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public String getFoto() {
        return foto;
    }

}
