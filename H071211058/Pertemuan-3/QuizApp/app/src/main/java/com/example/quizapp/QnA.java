package com.example.quizapp;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QnA extends AppCompatActivity implements View.OnClickListener {

    TextView questAmount, question;
    Button answerA, answerB, answerC, answerD;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    int amountOfQuestion = 1;
    int skor;
    int highestScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna);

        questAmount = findViewById(R.id.questAmount);
        question = findViewById(R.id.question);
        answerA = findViewById(R.id.answerA);
        answerB = findViewById(R.id.answerB);
        answerC = findViewById(R.id.answerC);
        answerD = findViewById(R.id.answerD);

        skor = getIntent().getIntExtra("total_score",0);

        highestScore = getIntent().getIntExtra("best_score", 0);

        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);
        answerD.setOnClickListener(this);

        skor = 0;
        questAmount.setText("Question " + amountOfQuestion + " of " + totalQuestion);
        loadNewQuestion();

    }

    @Override
    public void onClick(View view) {

        answerA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.btnColor));
        answerB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.btnColor));
        answerC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.btnColor));
        answerD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.btnColor));


        Button clickedButton = (Button) view;

        if(clickedButton.getId() == R.id.answerA|| clickedButton.getId() == R.id.answerB || clickedButton.getId() == R.id.answerC || clickedButton.getId() == R.id.answerD){
            selectedAnswer = clickedButton.getText().toString();
            if(selectedAnswer.equals(QuestionAnswer.correctAns[currentQuestionIndex])){
                clickedButton.setBackgroundColor(Color.GREEN);
                skor = skor + 20;
            }else if(selectedAnswer != QuestionAnswer.correctAns[currentQuestionIndex]){
                clickedButton.setBackgroundColor(Color.RED);
            }
            currentQuestionIndex++;
            amountOfQuestion++;

            new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(currentQuestionIndex == totalQuestion){
                            String name = getIntent().getStringExtra("extra_name");
                            String profileImage = getIntent().getStringExtra("PROFILE_IMAGE_URI");


                            Intent intent = new Intent(getApplicationContext(), ScoreActivity.class);
                            intent.putExtra("extra_name", name);
                            intent.putExtra("PROFILE_IMAGE_URI", profileImage);
                            intent.putExtra("total_score", skor);
                            intent.putExtra("best_score", highestScore);
                            startActivity(intent);
                            return;
                        }else {
                            clickedButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.btnColor));
                            questAmount.setText("Question " + amountOfQuestion + " of " + totalQuestion);
                            loadNewQuestion();
                        }
                    }
                },1000);


        }

    }

    void loadNewQuestion(){
        //mengambil pertanyaan

        question.setText(QuestionAnswer.question[currentQuestionIndex]);

//        Random rand = new Random();
//        String randomQuestion = QuestionAnswer.question[rand.nextInt(6)];
//        question.setText(randomQuestion);

        //mengambil pilihan jawaban
        answerA.setText(QuestionAnswer.choice[currentQuestionIndex][0]);
        answerB.setText(QuestionAnswer.choice[currentQuestionIndex][1]);
        answerC.setText(QuestionAnswer.choice[currentQuestionIndex][2]);
        answerD.setText(QuestionAnswer.choice[currentQuestionIndex][3]);

    }
}