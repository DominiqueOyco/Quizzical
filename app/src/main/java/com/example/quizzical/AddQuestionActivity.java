package com.example.quizzical;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.quizzical.Helpers.InputValidation;
import com.example.quizzical.Model.Question;
import com.example.quizzical.SQL.DatabaseHelper2;

public class AddQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = AddQuestionActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutQuestion;
    private TextInputLayout textInputLayoutAnswer1;
    private TextInputLayout textInputLayoutAnswer2;
    private TextInputLayout textInputLayoutAnswer3;
    private TextInputLayout textInputLayoutAnswer4;
    private TextInputLayout textInputLayoutCorrect;

    private TextInputEditText textInputEditTextQuestion;
    private TextInputEditText textInputEditTextAnswer1;
    private TextInputEditText textInputEditTextAnswer2;
    private TextInputEditText textInputEditTextAnswer3;
    private TextInputEditText textInputEditTextAnswer4;
    private TextInputEditText textInputEditTextCorrect;

    private Button buttonSubmit;

    private InputValidation inputValidation;
    private DatabaseHelper2 databaseHelper2;
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutQuestion = (TextInputLayout) findViewById(R.id.textInputLayoutQuestion);
        textInputLayoutAnswer1 = (TextInputLayout) findViewById(R.id.textInputLayoutAnswer1);
        textInputLayoutAnswer2 = (TextInputLayout) findViewById(R.id.textInputLayoutAnswer2);
        textInputLayoutAnswer3 = (TextInputLayout) findViewById(R.id.textInputLayoutAnswer3);
        textInputLayoutAnswer4 = (TextInputLayout) findViewById(R.id.textInputLayoutAnswer4);
        textInputLayoutCorrect = (TextInputLayout) findViewById(R.id.textInputLayoutCorrect);

        textInputEditTextQuestion = (TextInputEditText) findViewById(R.id.textInputEditTextQuestion);
        textInputEditTextAnswer1 = (TextInputEditText) findViewById(R.id.textInputEditTextAnswer1);
        textInputEditTextAnswer2 = (TextInputEditText) findViewById(R.id.textInputEditTextAnswer2);
        textInputEditTextAnswer3 = (TextInputEditText) findViewById(R.id.textInputEditTextAnswer3);
        textInputEditTextAnswer4 = (TextInputEditText) findViewById(R.id.textInputEditTextAnswer4);
        textInputEditTextCorrect = (TextInputEditText) findViewById(R.id.textInputEditTextCorrect);

        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        buttonSubmit.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper2 = new DatabaseHelper2(activity);
        question = new Question();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonSubmit:
                postDataToSQLite();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextQuestion, textInputLayoutQuestion, getString(R.string.error_message))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextAnswer1, textInputLayoutAnswer1, getString(R.string.error_message))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextAnswer2, textInputLayoutAnswer2, getString(R.string.error_message))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextAnswer3, textInputLayoutAnswer3, getString(R.string.error_message))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextAnswer4, textInputLayoutAnswer4, getString(R.string.error_message))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextCorrect, textInputLayoutCorrect, getString(R.string.error_message))) {
            return;
        }

        if (!databaseHelper2.checkQuestion(textInputEditTextQuestion.getText().toString().trim())) {

            question.setQuestion(textInputEditTextQuestion.getText().toString().trim());
            question.setAnswer1(textInputEditTextAnswer1.getText().toString().trim());
            question.setAnswer2(textInputEditTextAnswer2.getText().toString().trim());
            question.setAnswer3(textInputEditTextAnswer3.getText().toString().trim());
            question.setAnswer4(textInputEditTextAnswer4.getText().toString().trim());
            question.setCorrect(textInputEditTextCorrect.getText().toString().trim());

            databaseHelper2.addQuestion(question);


            emptyInputEditText();

            finish();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_question_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextQuestion.setText(null);
        textInputEditTextAnswer1.setText(null);
        textInputEditTextAnswer2.setText(null);
        textInputEditTextAnswer3.setText(null);
        textInputEditTextAnswer4.setText(null);
        textInputEditTextCorrect.setText(null);
    }

}
