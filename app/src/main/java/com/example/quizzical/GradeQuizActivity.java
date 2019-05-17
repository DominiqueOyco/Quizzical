package com.example.quizzical;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.example.quizzical.Model.Question;
import com.example.quizzical.SQL.DatabaseHelper2;

import java.util.ArrayList;
import java.util.List;


public class GradeQuizActivity extends AppCompatActivity {

    int score = QuizActivity.getScore();

    private int questionCountTotal;

    private TextView textViewGrade;
    private ArrayList<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_quiz);

        DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(this);
        questionList = databaseHelper2.getAllQuestion();
        questionCountTotal = questionList.size();

        textViewGrade = findViewById(R.id.text_view_grade);
        textViewGrade.setText("Your grade is " + score + "/" + questionCountTotal);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout( (int) (width*.75), (int) (height*.50));
    }
}
