package com.example.tuprak6;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<ModalChat> arrayList = generateDummyModalChat();

    private static ArrayList<ModalChat> generateDummyModalChat() {
        ArrayList<ModalChat> arrayList = new ArrayList<>();
        arrayList.add(new ModalChat("Patrick", R.drawable.patrick, "+62 812-3456-7890", "bintang laut", "Februari 26, 2021", "Spongebob? Psssttttttt,Spongebob?? Pssssssssssssst.", "11.51"));
        arrayList.add(new ModalChat("Plankton", R.drawable.plankton, "+62 813-4567-8901", "Chumb Bucket", "November 19, 2022", "Dimana krabs menyimpan resep rahasianya?", "07.30"));
        arrayList.add(new ModalChat("Squidward", R.drawable.squidward, "+62 815-6759-0103", "Bermain klarinet dan melukis", "Oktober 9, 2021", "Aku harus kembali bekerja", "11.29"));
        arrayList.add(new ModalChat("Sendy", R.drawable.sendy, "+62 878-9012-3456", "Texas", "Desember 15, 2020", "aku suka bermain karate", "09.10"));
        arrayList.add(new ModalChat("Mrs. Puff", R.drawable.mrspuff, "+62 877-6543-2109", "guru mengemudi", "April 15, 2020", "kamu lulus tes mengemudi, selamat yah", "15.21"));
        arrayList.add(new ModalChat("Mr. Krabs", R.drawable.krabs, "+62 896-5432-1098", "krabs suka uang", "November 30,2020", "tolong buatkan 2 Krabby Patty", "12.55"));
        arrayList.add(new ModalChat("Bill", R.drawable.bill, "+62 819-8765-4321", "just ikan", "Agustus 30, 2021", "saya pesan 1 krabby patty tanpa acar", "11.00"));
        arrayList.add(new ModalChat("Pearl", R.drawable.pearl, "+62 838-9012-3456", "Pearl Krabs", "Mei 12, 2020", "dimana ayahku?", "08.19"));
        arrayList.add(new ModalChat("Mermaid Man", R.drawable.mermaidman, "+62 821-0987-6543", "superhero pensiun", "Juli 26, 2022", "semangat nak", "17.08"));
        arrayList.add(new ModalChat("Fred", R.drawable.fred, "+62 811-2345-6789", "Bikini Bottom", "Januari 01, 2023", "ouh, tidak", "12.00"));
        arrayList.add(new ModalChat("Barnacle Boy", R.drawable.barnacleboy, "+62 852-6789-0123", "rekan Mermaid Man", "Oktober 19, 2020", "jangan ganggu mermaid man", "14.44"));
        return arrayList;
    }
}
