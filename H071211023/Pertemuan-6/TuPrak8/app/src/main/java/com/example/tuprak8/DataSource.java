package com.example.tuprak8;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<DataUpload> arrayList = generateDummyModalChat();

    private static ArrayList<DataUpload> generateDummyModalChat() {
        ArrayList<DataUpload> arrayList = new ArrayList<>();
        arrayList.add(new DataUpload("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor", "https://i.pinimg.com/originals/04/5b/99/045b99bc4b16122a23b840e73db8d1dc.jpg", "lexiee", "lexter", "https://i.pinimg.com/originals/04/5b/99/045b99bc4b16122a23b840e73db8d1dc.jpg"));
        arrayList.add(new DataUpload("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor", "https://pbs.twimg.com/media/FweOZYkaAAA6BeS?format=jpg&name=900x900", "oliv", "Olivia", "https://pbs.twimg.com/media/FweOZYkaAAA6BeS?format=jpg&name=900x900"));
        arrayList.add(new DataUpload("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor", "https://pbs.twimg.com/media/FweOhi5aQAAWDgZ?format=jpg&name=small", "gring", "Garret", "https://pbs.twimg.com/media/FweOhi5aQAAWDgZ?format=jpg&name=small"));
        arrayList.add(new DataUpload("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor", "https://pbs.twimg.com/media/FweOZOlaMAAvA05?format=jpg&name=small", "medusa", "lamia", "https://pbs.twimg.com/media/FweOZOlaMAAvA05?format=jpg&name=small"));
        arrayList.add(new DataUpload("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor", "https://pbs.twimg.com/media/FweKwVEaAAAv2Mr?format=jpg&name=900x900", "agypt", "Aegypt Bellen", "https://pbs.twimg.com/media/FweKwVEaAAAv2Mr?format=jpg&name=900x900"));
        arrayList.add(new DataUpload("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor", "https://pbs.twimg.com/media/FwfSsIaaEAEsNeY?format=jpg&name=900x900", "agatha", "Agatha Bellen", "https://pbs.twimg.com/media/FwfSsIaaEAEsNeY?format=jpg&name=900x900"));
        arrayList.add(new DataUpload("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor", "https://pbs.twimg.com/media/FweOZ1TaAAIU-n9?format=jpg&name=900x900", "castell", "Castell Bellen", "https://pbs.twimg.com/media/FweOZ1TaAAIU-n9?format=jpg&name=900x900"));
        arrayList.add(new DataUpload("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor", "https://pbs.twimg.com/media/FweOh-3agAAo0Ff?format=jpg&name=small", "monn", "Simon", "https://pbs.twimg.com/media/FweOh-3agAAo0Ff?format=jpg&name=small"));
        arrayList.add(new DataUpload("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor", "https://pbs.twimg.com/media/FweNLM4akAA2GQ5?format=jpg&name=900x900", "kiddo", "Athan Bellen", "https://pbs.twimg.com/media/FweNLM4akAA2GQ5?format=jpg&name=900x900"));
        arrayList.add(new DataUpload("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor", "https://i.pinimg.com/originals/05/ee/5d/05ee5d1af906f3ce9651e31c5454cfcc.jpg", "nevan", "Nevan Arnault", "https://i.pinimg.com/originals/05/ee/5d/05ee5d1af906f3ce9651e31c5454cfcc.jpg"));


        return arrayList;
    }
    // untuk pencarian data pada arraylist
    public static ArrayList<DataUpload> searchDataUpload(String query) {
        ArrayList<DataUpload> searchedDataUpload = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            final DataUpload dataUpload = arrayList.get(i);
            String q = query.toLowerCase();
            String fullName = dataUpload.getFullName().toLowerCase();
            String userName = dataUpload.getUserName().toLowerCase();
            if (fullName.startsWith(query) || userName.startsWith(query)) {
                searchedDataUpload.add(dataUpload);
            }
        }
        return searchedDataUpload;
    }
}
