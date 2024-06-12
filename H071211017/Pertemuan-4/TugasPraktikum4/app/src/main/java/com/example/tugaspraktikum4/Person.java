package com.example.tugaspraktikum4;

public class Person {
    private int image;
    private String nama;
    private String nomor;
    private String status;
    private String lastStatusChanged;
    private String time;
    private String chat;

    public Person(int image, String nama, String nomor, String status, String lastStatusChanged, String time, String chat) {
        this.image = image;
        this.nama = nama;
        this.nomor = nomor;
        this.status = status;
        this.lastStatusChanged = lastStatusChanged;
        this.time = time;
        this.chat = chat;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastStatusChanged() {
        return lastStatusChanged;
    }

    public void setLastStatusChanged(String lastStatusChanged) {
        this.lastStatusChanged = lastStatusChanged;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String waktu) {
        this.time = waktu;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
}
