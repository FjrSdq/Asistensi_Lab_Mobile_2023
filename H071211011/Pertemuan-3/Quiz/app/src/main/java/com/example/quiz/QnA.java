package com.example.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QnA {
    private String question;
    private String [] answer;
    private int score;
    private int correctAnswer;


    public QnA(String question, String[] answer, int score, int correctAnswer) {
        this.question = question;
        this.answer = answer;
        this.score = score;
        this.correctAnswer = correctAnswer;

    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswer() {
        return answer;
    }

    public int getScore() {
        return score;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }


    public static List<QnA> getAllQuestion(){
        List<QnA> qna = new ArrayList<>();
        qna.add(new QnA("Negara terluas keempat di dunia adalah",
                new String[] {"Amerika Serikat","Jepang","Indonesia","Korea"}, 20,0));
        qna.add(new QnA("Anjing Pitbull berasal dari negara",
                new String[] {"Jerman","Inggris","Belanda","Turki"}, 20,1));
        qna.add(new QnA("Penguasa tersadis dari Jerman adalah",
                new String[] {"LeopoldQueen", "Mary I Joseph", "StalinAdolf", "Adolf Hitler"}, 20,3));
        qna.add(new QnA("Ledakan bintang di galaksi disebut",
                new String[] {"Aberasi","Hujan Meteor","Supernova","Elongasi"}, 20,2));
        qna.add(new QnA("Tokoh utama film “Toy Story” adalah",
                new String[] {"Buzz Lightyear","Mr.Potato Head","Slinky dog","Woody"}, 20,3));
        qna.add(new QnA("Satuan bunyi adalah",
                new String[] {"Desibel","Candela","Newton","Kelvin"}, 20,0));
        qna.add(new QnA("Burung tercepat di dunia adalah",
                new String[] {"Merpati","Falcon","Kasuari","Gagak"}, 20,1));
        qna.add(new QnA("Sepakbola Piala Dunia tahun 2010 diselenggarakan di",
                new String[] {"Jerman","Brazil","Inggris","Afrika Selatan"}, 20,3));
        qna.add(new QnA("Benua biru adalah sebutan bagi benua",
                new String[] {"Eropa","Afrika","Amerika Serikat","Australia"}, 20,0));
        qna.add(new QnA("Kereta api ditemukan oleh",
                new String[] {" William Murdoch","Robert Fulton","Nikola Tesla","Benyamin Holt"}, 20,0));

        Collections.shuffle(qna);

        return qna.subList(0,5);
    }
}
