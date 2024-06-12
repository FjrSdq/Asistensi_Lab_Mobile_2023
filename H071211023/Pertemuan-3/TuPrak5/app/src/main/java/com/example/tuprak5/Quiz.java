package com.example.tuprak5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz {
    private String question;
    private String[] option;
    private int score;
    private int answer;

    public Quiz(String question, String[] option, int score, int answer) {
        this.question = question;
        this.option = option;
        this.score = score;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOption() {
        return option;
    }

    public int getScore() {
        return score;
    }

    public int getAnswer() {
        return answer;
    }

    public static List<Quiz> getAllQuiz(){
        List<Quiz> QnA = new ArrayList<>();
        QnA.add(new Quiz("Siapa penulis teks proklamasi kemerdekaan Indonesia?",
                new String[]{"Soekarno", "Tan Malaka", "Sutan Sjahrir", "Mohammad Hatta"}, 40,0));
        QnA.add(new Quiz("Daerah yang merupakan kepulauan di provinsi Sulawesi Selatan adalah",
                new String[]{"Kab. Soppeng", "Kab. Barru", "Kab. Pangkep", "Kab. Maros"}, 40,2));
        QnA.add(new Quiz("Wilayah daerah yang termasuk rumpun suku Makassar ",
                new String[]{"Kab. Maros, Kab Jeneponto, Kab. Takalar", "Kab. Pangkep, Kab. Barru, Kab. Enrekang", "Kab. Bulukumba, Kab. Sinjai, Kab. Bantaeng", "Kab. Takalar, Kab. Pinrang, Kab. Sidrap"}, 40,0));
        QnA.add(new Quiz("Siapa tokoh pergerakan nasional Indonesia yang dikenal dengan julukan Bung Karno?",
                new String[]{"Abdurrahman Wahid", "Mohammad Hatta", "Soeharto", "Soekarno"}, 40,3));
        QnA.add(new Quiz("Apa kepanjangan dari singkatan PSE",
                new String[]{"Pendaftaran Sistem Elektronik", "Penyelenggara Sistem Elektronik", "Pemblokiran Sistem Elektronik", "Pembuatan Sistem Elektronik"}, 40,1));
        QnA.add(new Quiz("Apa nama sungai terpanjang di Indonesia?",
                new String[]{"Kapuas", "Amazon", "Nile", "Mahakam"}, 40,0));
        QnA.add(new Quiz("Apa nama pulau terkecil di Indonesia",
                new String[]{"Pulau Jawa", "Pulau Simping", "Pulau Samalona ", "Pulau Seribu"}, 40,1));
        QnA.add(new Quiz("Ibu Kota Provinsi Jawa Tengah adalah",
                new String[]{"Yogyakarta", "Solo", "Semarang", "Surakarta"}, 40,2));
        QnA.add(new Quiz("Makanan khas Makassar adalah Coto Makassar. Resep pembuatannya dari asli Bugis, Coto Makassar dibuat dengan air",
                new String[]{"Kelapa", "Santan", "Suji", "Tajin"}, 40,3));
        QnA.add(new Quiz("Tokoh yang ada di pecahan uang Rp20.000,00 TE 2022 adalah",
                new String[]{"Ir. H. Djuanda Kartawidjaja", "Dr. K.H. Idham Chalid", "Mohammad Hoesni Thamrin", "Dr. G.S.S.J. Ratulangi"}, 40,3));
        Collections.shuffle(QnA);
        return QnA.subList(0,5);
    }
}
