package com.example.mamamsehat;

public class RegisterData {
    private String nama, email, password, type;

    public RegisterData(){

    }

    public RegisterData(String nama, String email, String password, String type) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
