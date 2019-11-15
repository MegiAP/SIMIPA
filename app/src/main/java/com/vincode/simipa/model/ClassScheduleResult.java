package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassScheduleResult {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("kodeMK")
        @Expose
        private String kodeMK;
        @SerializedName("Mata Kuliah")
        @Expose
        private String mataKuliah;
        @SerializedName("Ruang")
        @Expose
        private String ruang;
        @SerializedName("Hari")
        @Expose
        private String hari;
        @SerializedName("Tahun Akademik")
        @Expose
        private String tahunAkademik;
        @SerializedName("Semester")
        @Expose
        private String semester;
        @SerializedName("Jenis")
        @Expose
        private String jenis;
        @SerializedName("Kelas")
        @Expose
        private String kelas;
        @SerializedName("Nip1")
        @Expose
        private String nip1;
        @SerializedName("Dosen pj")
        @Expose
        private String dosenPj;
        @SerializedName("Mulai")
        @Expose
        private String mulai;
        @SerializedName("Selesai")
        @Expose
        private String selesai;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKodeMK() {
            return kodeMK;
        }

        public void setKodeMK(String kodeMK) {
            this.kodeMK = kodeMK;
        }

        public String getMataKuliah() {
            return mataKuliah;
        }

        public void setMataKuliah(String mataKuliah) {
            this.mataKuliah = mataKuliah;
        }

        public String getRuang() {
            return ruang;
        }

        public void setRuang(String ruang) {
            this.ruang = ruang;
        }

        public String getHari() {
            return hari;
        }

        public void setHari(String hari) {
            this.hari = hari;
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

        public String getJenis() {
            return jenis;
        }

        public void setJenis(String jenis) {
            this.jenis = jenis;
        }

        public String getKelas() {
            return kelas;
        }

        public void setKelas(String kelas) {
            this.kelas = kelas;
        }

        public String getNip1() {
            return nip1;
        }

        public void setNip1(String nip1) {
            this.nip1 = nip1;
        }

        public String getDosenPj() {
            return dosenPj;
        }

        public void setDosenPj(String dosenPj) {
            this.dosenPj = dosenPj;
        }

        public String getMulai() {
            return mulai;
        }

        public void setMulai(String mulai) {
            this.mulai = mulai;
        }

        public String getSelesai() {
            return selesai;
        }

        public void setSelesai(String selesai) {
            this.selesai = selesai;
        }

    }

