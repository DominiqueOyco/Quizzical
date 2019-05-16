package com.example.quizzical;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.quizzical.R;
import com.example.quizzical.Adapters.QuestionRecyclerAdapter;
import com.example.quizzical.Model.Question;
import com.example.quizzical.SQL.DatabaseHelper2;

import java.util.ArrayList;
import java.util.List;

public class QuestionListActivity extends AppCompatActivity {

    private AppCompatActivity activity = QuestionListActivity.this;
    private Button addQuestion;
    private RecyclerView recyclerViewQuestion;
    private List<Question> listQuestion;
    private QuestionRecyclerAdapter QuestionRecyclerAdapter;
    private DatabaseHelper2 databaseHelper2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        getSupportActionBar().setTitle("");
        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        addQuestion = (Button) findViewById(R.id.buttonAddQ);
        recyclerViewQuestion = (RecyclerView) findViewById(R.id.recyclerViewQuestion);
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionListActivity.this, AddQuestionActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listQuestion = new ArrayList<>();
        QuestionRecyclerAdapter = new QuestionRecyclerAdapter(listQuestion);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewQuestion.setLayoutManager(mLayoutManager);
        recyclerViewQuestion.setItemAnimator(new DefaultItemAnimator());
        recyclerViewQuestion.setHasFixedSize(true);
        recyclerViewQuestion.setAdapter(QuestionRecyclerAdapter);
        databaseHelper2 = new DatabaseHelper2(activity);

        getDataFromSQLite();
    }

    /**
     * This method is to fetch all question records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listQuestion.clear();
                listQuestion.addAll(databaseHelper2.getAllQuestion());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                QuestionRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

}
