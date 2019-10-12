package com.example.lab2_mob204.model;

public class Book {
    private String masach;
    private String matheloai;
    private String tieude;
    private String tacgia;
    private String nhaxuatban;
    private String giabia;

    public Book() {
    }

    public Book(String masach, String matheloai, String tieude, String tacgia, String nhaxuatban, String giabia, String soluong) {
        this.masach = masach;
        this.matheloai = matheloai;
        this.tieude = tieude;
        this.tacgia = tacgia;
        this.nhaxuatban = nhaxuatban;
        this.giabia = giabia;
        this.soluong = soluong;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(String matheloai) {
        this.matheloai = matheloai;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNhaxuatban() {
        return nhaxuatban;
    }

    public void setNhaxuatban(String nhaxuatban) {
        this.nhaxuatban = nhaxuatban;
    }

    public String getGiabia() {
        return giabia;
    }

    public void setGiabia(String giabia) {
        this.giabia = giabia;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    private String soluong;




}
