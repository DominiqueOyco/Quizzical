package com.example.quizzical;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class AccountActivity extends AppCompatActivity{

    private TextView username;
    private TextView password;
    private Button edit;
    private Button back;

    String userValue;
    String passValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //getting data from fields on the activity account xml
        username = (TextView)findViewById(R.id.userText);
        password = (TextView)findViewById(R.id.userPass);
        edit = (Button)findViewById(R.id.b_edit_account);
        back = (Button)findViewById(R.id.b_back_to_home);

        //accepts the data passed from userHomeActivity
        userValue = getIntent().getStringExtra("New Username");
        passValue = getIntent().getStringExtra("New Password");

        //set the textviews from the xml file to the passed data
        username.setText("USERNAME: " + userValue);
        password.setText("PASSWORD: " + passValue);

        //once clicked, user gets directed to the signup page to create a new account
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //once clicked user gets directed to the home page
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, UserHomeNavDrawerActivity.class);
                startActivity(intent);
            }
        });
    }
}