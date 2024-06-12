package com.example.tugaspraktek4;

import java.util.ArrayList;

public class chatAppData {
    private static String profileName[] = {
            "Aether",
            "Lumine",
            "Diluc",
            "Kaeya",
            "Jean",
            "Lisa",
            "Fishcl",
            "Amber",
            "Barbara",
    };
    private static String profileDetail[] = {
            "Male MC",
            "Female MC",
            "Batman",
            "Sus from birth",
            "Dandelion",
            "1st Electro Mommy",
            "Chuuni",
            "First companion",
            "First healer",
    };

    private static String profileNumber[] = {
            "A traveler from reaches unknown, has a twin sister",
            "A traveler from reaches unknown, has a twin brother",
            "Daytime conglomerate, Nighttime vigilante",
            "A Cavalry Captain Knight, from a town with no horses...?",
            "Acting Grandmaster of a Knight corps that's severely incompetent",
            "I repeat, RETURN YOUR BOOKS ON TIME",
            "Once you get used to her, she's really sweet",
            "A cinnamon roll that's worth protecting",
            "A sister that is DEFINITELY WORTH PROTECTING",
    };
    private static int profileImage[] = {
            R.drawable.picture_1,
            R.drawable.picture_2,
            R.drawable.picture_3,
            R.drawable.picture_4,
            R.drawable.picture_5,
            R.drawable.picture_6,
            R.drawable.picture_7,
            R.drawable.picture_8,
            R.drawable.picture_9,
    };
    static ArrayList<Data> getListData(){
        ArrayList<Data> list = new ArrayList<>();
        for (int i = 0; i < profileName.length; i++){
            Data data = new Data();
            data.setName(profileName[i]);
            data.setDetail(profileDetail[i]);
            data.setPhoto(profileImage[i]);
            data.setNumber(profileNumber[i]);
            list.add(data);
        }
        return list;
    }
}