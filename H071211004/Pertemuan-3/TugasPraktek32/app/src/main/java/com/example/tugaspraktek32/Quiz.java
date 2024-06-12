package com.example.tugaspraktek32;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Quiz extends AppCompatActivity implements View.OnClickListener {

    int totalScore = 0;
    private final String EXTRA_PROFILE_PICTURE = "EXTRA_PROFILE_PICTURE";
    private final String EXTRA_USERNAME = "EXTRA_USERNAME";
    private final String EXTRA_SCORE = "EXTRA_SCORE";
    private final String EXTRA_HIGHSCORE = "EXTRA_HIGHSCORE";
    TextView totalQues, questions;
    Button ans_A, ans_B, ans_C, ans_D;
    String[][] quizzes = {
            {"What is the real name of the Geo Archon ?", "Morax", "Rex Lapis", "Zhongli", "Babah Jong Lee", "100"},
            {"What do you call someone who rates the fate series 5 and below ?", "Yusran", "Normie", "Trash Taste", "Dumbsh*t", "100"},
            {"What is the name of Shadow's Ultimate technique ?", "I AM ATOMIC", "Explosion", "Mob-fu", "Fireball", "100"},
            {"What do you give a Hu Tao player ?", "Staff of Homa", "A party with a healer", "A shovel", "A casket", "100"},
            {"What do you call someone who denies that they are a weeb ?", "Wibu denial", "Otaku", "Weeb", "Tmn kak takdim", "100"},
            {"What do you call an audio equipment that goes on your ears, and has a microphone ?", "Headset", "Headphone", "Earphone", "Speaker", "100"},
            {"What is 1+1", "a problem", "2", "Two", "a formula", "100"},
            {"What is the most important aspect in enjoying an anime ?", "Personal enjoyment", "Animation and Story", "PLOT", "Fanservice", "100"},
            {"What is this ?", "a question", "a quiz app", "a task for mobile programming", "idk, ask god", "100"},
            {"I ran out of ideas for a question, so just pick anything", "Understandable", "Ok ?", "Weak", "Have a nice day", "100"}
    };
    int[] chosen_quiz = generateRandomNumbers(5, 0, 9);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questions = findViewById(R.id.questions);
        totalQues = findViewById(R.id.totalQues);
        ans_A = findViewById(R.id.ans_A);
        ans_B = findViewById(R.id.ans_B);
        ans_C = findViewById(R.id.ans_C);
        ans_D = findViewById(R.id.ans_D);

        ans_A.setOnClickListener(this);
        ans_B.setOnClickListener(this);
        ans_C.setOnClickListener(this);
        ans_D.setOnClickListener(this);

        int[] rando_ans = generateRandomNumbers(4, 1, 4);
        questions.setText(quizzes[chosen_quiz[0]][0]);
        ans_A.setText(quizzes[chosen_quiz[0]][rando_ans[0]]);
        ans_B.setText(quizzes[chosen_quiz[0]][rando_ans[1]]);
        ans_C.setText(quizzes[chosen_quiz[0]][rando_ans[2]]);
        ans_D.setText(quizzes[chosen_quiz[0]][rando_ans[3]]);

    }

    public static int[] generateRandomNumbers(int count, int min, int max) {
        Random random = new Random();
        Set<Integer> generated = new HashSet<>();
        int[] randomNumbers = new int[count];
        for (int i = 0; i < count; i++) {
            int number;
            do {
                number = random.nextInt((max - min) + 1) + min;
            } while (generated.contains(number));
            randomNumbers[i] = number;
            generated.add(number);
        }
        return randomNumbers;
    }

    @Override
    public void onClick(View view) {
        Handler handler = new Handler();
        int i = Integer.parseInt(totalQues.getText().toString());
        int[] rando_ans = generateRandomNumbers(4, 1, 4);
        String key_ans = quizzes[chosen_quiz[i-1]][1];
        if (i == 5) {

            if (view instanceof Button) {
                Button button = (Button) view;
                String selectAns = button.getText().toString();

                if (selectAns.equals(key_ans)) {
                    totalScore += Integer.valueOf(quizzes[chosen_quiz[i - 1]][5]);
                    button.setBackgroundTintList(getResources().getColorStateList(R.color.green,null));
                } else {
                    button.setBackgroundTintList(getResources().getColorStateList(R.color.red,null));
                }
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = getIntent();
                    String profileUriString = intent.getStringExtra(EXTRA_PROFILE_PICTURE);
                    String user_name = intent.getStringExtra(EXTRA_USERNAME);
                    String topScore = intent.getStringExtra(EXTRA_HIGHSCORE);
                    Uri profile_picture = Uri.parse(profileUriString);
                    Intent scoreIntent = new Intent(Quiz.this, Score.class);
                    scoreIntent.putExtra(EXTRA_USERNAME, user_name);
                    scoreIntent.putExtra(EXTRA_PROFILE_PICTURE, profile_picture.toString());
                    scoreIntent.putExtra(EXTRA_SCORE, String.valueOf(totalScore));
                    scoreIntent.putExtra(EXTRA_HIGHSCORE, topScore);
                    startActivity(scoreIntent);
                }
            }, 1000);

        } else {
            if (view instanceof Button) {
                Button button = (Button) view;
                String selectAns = button.getText().toString();

                if (selectAns.equals(key_ans)) {
                    totalScore += Integer.valueOf(quizzes[chosen_quiz[i]][5]);
                    button.setBackgroundTintList(getResources().getColorStateList(R.color.green,null));
                } else {
                    button.setBackgroundTintList(getResources().getColorStateList(R.color.red,null));
                }

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button.setBackgroundTintList(getResources().getColorStateList(R.color.black,null));
                        questions.setText(quizzes[chosen_quiz[i]][0]);
                        ans_A.setText(quizzes[chosen_quiz[i]][rando_ans[0]]);
                        ans_B.setText(quizzes[chosen_quiz[i]][rando_ans[1]]);
                        ans_C.setText(quizzes[chosen_quiz[i]][rando_ans[2]]);
                        ans_D.setText(quizzes[chosen_quiz[i]][rando_ans[3]]);
                        totalQues.setText(String.valueOf(i+1));
                    }
                },1000);
            }
        }
    }
}