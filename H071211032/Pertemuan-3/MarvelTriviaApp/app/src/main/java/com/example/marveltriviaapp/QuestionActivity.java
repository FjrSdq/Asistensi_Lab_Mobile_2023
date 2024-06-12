package com.example.marveltriviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    TextView questionnumber;

    TextView question;

    Button option1, option2, option3, option4;

    int totalquestions = 5;

    int currentquestion = 0;

    int score = 0;

    int currentQuestionIndex;

    Random random;

    User user;

    Photo foto;

    ArrayList<Integer> numbers = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = findViewById(R.id.tv_question);
        questionnumber = findViewById(R.id.question_number);
        option1 = findViewById(R.id.option_1);
        option2 = findViewById(R.id.option_2);
        option3 = findViewById(R.id.option_3);
        option4 = findViewById(R.id.option_4);
        user = getIntent().getParcelableExtra("user");
        foto = getIntent().getParcelableExtra("foto");

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option2.setOnClickListener(this);
        option2.setOnClickListener(this);

        for (int i = 0; i <= 9; i++) {
            numbers.add(i);
        }

        randomQuestion();
    }


    public void onClick(View view) {
        Button clickedButton = (Button) view;
        String clickedAnswer = ((Button) view).getText().toString().trim();
        if (clickedAnswer.equalsIgnoreCase(Answers.correctAnswers[currentQuestionIndex])) {
            clickedButton.setBackgroundColor(getResources().getColor(R.color.GREEN));
            score += 20;
        } else {
            clickedButton.setBackgroundColor(getResources().getColor(R.color.RED));
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                randomQuestion();
            }
        }, 1000);

    }

    public void randomQuestion() {
        int index = random.nextInt(numbers.size());
        currentQuestionIndex = numbers.remove(index);
        currentquestion++;
        loadNewQuestions();
    }

    private void loadNewQuestions() {
        if (currentquestion <= totalquestions) {
            questionnumber.setText(String.format("Quiz %d of %d", currentquestion, totalquestions));
            option1.setBackgroundColor(getResources().getColor(R.color.white));
            option2.setBackgroundColor(getResources().getColor(R.color.white));
            option3.setBackgroundColor(getResources().getColor(R.color.white));
            option4.setBackgroundColor(getResources().getColor(R.color.white));

            question.setText(Answers.question[currentQuestionIndex]);
            option1.setText(Answers.choices[currentQuestionIndex][0]);
            option2.setText(Answers.choices[currentQuestionIndex][1]);
            option3.setText(Answers.choices[currentQuestionIndex][2]);
            option4.setText(Answers.choices[currentQuestionIndex][3]);
        } else {
            Intent intent = new Intent(QuestionActivity.this, ScoreActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("user", user);
            intent.putExtra("foto", foto);
            startActivity(intent);
        }
    }

}

