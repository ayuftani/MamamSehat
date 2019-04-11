package com.example.mamamsehat;

public class Card {
    String imagePath,nama,harga,deskripsi;

    public Card(String imagePath, String nama, String harga, String deskripsi) {
        this.imagePath = imagePath;
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
}
