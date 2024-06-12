package com.example.tugaspraktikum4;

public class Message {
    private String name;
    private String message;
    private String time;
    private boolean isMine;

    public Message(String name, String message, String time, boolean isMine) {
        this.name = name;
        this.message = message;
        this.time = time;
        this.isMine = isMine;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public boolean isMine() {
        return isMine;
    }
}
