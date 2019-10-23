package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile {

    @SerializedName("Npm")
    @Expose
    private String npm;

    @SerializedName("Display Name")
    @Expose
    private String displayName;

    @SerializedName("Email")
    @Expose
    private String email;

    @SerializedName("Jenis Kelamin")
    @Expose
    private String jenisKelamin;

    @SerializedName("No Ponsel")
    @Expose
    private String noPonsel;

    @SerializedName("Agama")
    @Expose
    private String agama;

    @SerializedName("Tempat Lahir")
    @Expose
    private Object tempatLahir;

    @SerializedName("Tanggal Lahir")
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

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNoPonsel() {
        return noPonsel;
    }

    public void setNoPonsel(String noPonsel) {
        this.noPonsel = noPonsel;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public Object getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(Object tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
