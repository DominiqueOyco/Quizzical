package com.example.quizzical;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzical.Model.Question;
import com.example.quizzical.SQL.DatabaseHelper2;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private TextView textViewQuestionCount;
    private TextView textViewTimer;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonNext;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    public static int score;

    private Boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        score = 0;

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewTimer = findViewById(R.id.text_view_timer);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttonNext = findViewById(R.id.button_next);

        DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(this);
        questionList = databaseHelper2.getAllQuestion();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
           if (questionCounter < questionCountTotal) {
               currentQuestion = questionList.get(questionCounter);

               textViewQuestion.setText(currentQuestion.getQuestion());
               rb1.setText(currentQuestion.getAnswer1());
               rb2.setText(currentQuestion.getAnswer2());
               rb3.setText(currentQuestion.getAnswer3());
               rb4.setText(currentQuestion.getAnswer4());

               questionCounter++;
               textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
               answered = false;

               if (questionCounter < questionCountTotal) {
                   buttonNext.setText("Next");
               } else {
                   buttonNext.setText("Finish");
               }

           } else {
               showScore();
           }
    }

    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answer = rbGroup.indexOfChild(rbSelected) + 1;

        if ( answer == Integer.parseInt(currentQuestion.getCorrect())) {
            score++;
        }
    }

    public static int getScore(){
        return score;
    }

    private void showScore() {
        Intent intent = new Intent(QuizActivity.this, GradeQuizActivity.class);
        startActivity(intent);
        finishQuiz();
    }

    private void finishQuiz() {
        finish();
    }

}
