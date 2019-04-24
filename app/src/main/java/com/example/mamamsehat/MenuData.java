package com.example.mamamsehat;

public class MenuData {
    private String nama, harga, deskripsi, imgUrl;

    public MenuData(String nama, String harga, String deskripsi, String imgUrl) {
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;
        this.imgUrl = imgUrl;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
