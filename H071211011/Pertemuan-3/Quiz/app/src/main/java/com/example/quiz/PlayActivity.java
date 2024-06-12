package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import java.util.List;

public class PlayActivity extends AppCompatActivity {
    private int currentIndex = 0, userScore = 0;
    private List<QnA> questionList;
    public static final String EXTRA_PLAYER = "extra_player";
    private TextView[] tvAnswer;
    private TextView tv_quest, textView1, answer1, answer2, answer3, answer4;
    private View[] viewAnswer;
    private View view1, view2, view3, view4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        answer1 = findViewById(R.id.tv_answ1);
        answer2 = findViewById(R.id.tv_answ2);
        answer3 = findViewById(R.id.tv_answ3);
        answer4 = findViewById(R.id.tv_answ4);
        textView1 = findViewById(R.id.textView);
        tv_quest = findViewById(R.id.tv_quest);
        view1 = findViewById(R.id.v_answer1);
        view2 = findViewById(R.id.v_answer2);
        view3 = findViewById(R.id.v_answer3);
        view4 = findViewById(R.id.v_answer4);

        questionList = QnA.getAllQuestion();

        tvAnswer = new TextView[]{answer1, answer2, answer3, answer4};
        viewAnswer = new View[]{view1, view2, view3, view4};
        renderQuestion();
        tvAnswer[0].setOnClickListener(view -> onAnswerClicked(0));
        tvAnswer[1].setOnClickListener(view -> onAnswerClicked(1));
        tvAnswer[2].setOnClickListener(view -> onAnswerClicked(2));
        tvAnswer[3].setOnClickListener(view -> onAnswerClicked(3));
    }

    private void renderQuestion() {
        QnA questions = questionList.get(currentIndex);
        textView1.setText(String.format("%d of 5", currentIndex + 1));
        tv_quest.setText(questions.getQuestion());
        for (int i = 0; i < tvAnswer.length; i++) {
            tvAnswer[i].setText(questions.getAnswer()[i]);
        }
    }
    private void onAnswerClicked(int index) {
        String profileImage = getIntent().getStringExtra("extra_profile");
        QnA questions = questionList.get(currentIndex);
        boolean isTrue = index == questions.getCorrectAnswer();
        userScore += isTrue ? questions.getScore() : 0;

        viewAnswer[index].setBackground(isTrue ? getDrawable(R.drawable.bg_correct_answer) : getDrawable(R.drawable.bg_wrong_answer));

        new Handler().postDelayed(()->{
            if (currentIndex == 4) {

                Player player = getIntent().getParcelableExtra(EXTRA_PLAYER);
                Intent intent = new Intent(this, ScoreActivity.class);
                intent.putExtra(ScoreActivity.EXTRA_SCORE, userScore);
                intent.putExtra(EXTRA_PLAYER, player);
                intent.putExtra("extra_profile", profileImage);
                startActivity(intent);
            } else {
                viewAnswer[index].setBackground(getDrawable(R.drawable.bg_answer));
                currentIndex++;
                renderQuestion();
            }
        },1000);
    }
}