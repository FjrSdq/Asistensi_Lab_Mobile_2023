package com.example.tuprak5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    public static final String EXTRA_PLAYER = "extra player";
    private int currentIndex = 0, userScore =0;
    private TextView[] option;
    private TextView number, question, option1, option2, option3, option4;
    private List<Quiz> quizList;
    private NameUser nameUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        number = findViewById(R.id.no);
        question = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nameUser = getIntent().getParcelableExtra(EXTRA_PLAYER);

        quizList = Quiz.getAllQuiz();
        option = new TextView[]{option1, option2, option3, option4};

        renderQuiz();
        option[0].setOnClickListener(view -> onOptionCLicked(0));
        option[1].setOnClickListener(view -> onOptionCLicked(1));
        option[2].setOnClickListener(view -> onOptionCLicked(2));
        option[3].setOnClickListener(view -> onOptionCLicked(3));


    }
    private void renderQuiz(){
        Quiz quiz = quizList.get(currentIndex);
        number.setText(String.format("Quiz %d of 5", currentIndex +1));
        question.setText(quiz.getQuestion());
        for (int i = 0; i<option.length; i++){
            option[i].setText(quiz.getOption()[i]);
        }

    }
    private void onOptionCLicked(int index) {
        Quiz quiz = quizList.get(currentIndex);
        boolean isTrue = index == quiz.getAnswer();

        if (isTrue){
            option[index].setTextColor(Color.BLUE);
        }else {
            option[index].setTextColor(Color.RED);
        }


        userScore += isTrue ? quiz.getScore() : 0;

        new Handler().postDelayed(()-> {
            if (currentIndex == 4){
                NameUser nameUser = getIntent().getParcelableExtra(EXTRA_PLAYER);
                Intent next = new Intent(this, MainActivity3.class);
                next.putExtra(MainActivity3.EXTRA_SCORE, userScore);
                next.putExtra(MainActivity3.EXTRA_PLAYER, nameUser);
                startActivity(next);
            }else {
                currentIndex++;
                option[index].setTextColor(Color.BLACK);
                renderQuiz();

            }
        }, 500);
    }
}