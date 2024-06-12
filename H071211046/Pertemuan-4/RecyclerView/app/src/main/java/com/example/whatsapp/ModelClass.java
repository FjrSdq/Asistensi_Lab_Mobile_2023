package com.example.whatsapp;

public class ModelClass {
    private int imageview1;
    private String textview,textview1,textview2,textview3,textview4;

    ModelClass(int imageview1,String textview, String textview1, String textview2,String textview3,String textview4){
        this.imageview1 = imageview1;
        this.textview = textview;
        this.textview1 =  textview1;
        this.textview2 = textview2;
        this.textview3 = textview3;
        this.textview4 = textview4;
    }

    public int getImageview1() {
        return imageview1;
    }

    public String getTextview() {
        return textview;
    }

    public String getTextview1() {
        return textview1;
    }

    public String getTextview2() {
        return textview2;
    }

    public String getTextview3() {
        return textview3;
    }

    public String getTextview4() {
        return textview4;
    }

}
