package com.example.lab2_mob204.model;

public class TypeBook {
    private String matheloaitype;
    private String tentheloai;
    private String mota;
    private String vitri;

    public TypeBook(String matheloaitype, String tentheloai, String mota, String vitri) {
        this.matheloaitype = matheloaitype;
        this.tentheloai = tentheloai;
        this.mota = mota;
        this.vitri = vitri;
    }

    public TypeBook() {

    }

    public String getMatheloaitype() {
        return matheloaitype;
    }

    public void setMatheloaitype(String matheloaitype) {
        this.matheloaitype = matheloaitype;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }
}