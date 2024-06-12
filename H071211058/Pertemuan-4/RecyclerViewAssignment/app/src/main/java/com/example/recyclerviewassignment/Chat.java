package com.example.recyclerviewassignment;

public class Chat {

    private String name, chat, time, foto, status, tanggalStatus, nomor;

    public Chat(String name, String chat, String time, String foto, String status, String tanggalStatus, String nomor) {
        this.name = name;
        this.chat = chat;
        this.time = time;
        this.foto = foto;
        this.status = status;
        this.tanggalStatus = tanggalStatus;
        this.nomor = nomor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggalStatus() {
        return tanggalStatus;
    }

    public void setTanggalStatus(String tanggalStatus) {
        this.tanggalStatus = tanggalStatus;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }
}
