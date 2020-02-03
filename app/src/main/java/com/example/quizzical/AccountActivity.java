package com.example.quizzical;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.example.quizzical.Model.User;
import com.example.quizzical.SQL.DatabaseHelper;

import java.util.List;

public class AccountActivity extends AppCompatActivity{

    String currentUser = MainActivity.currentUser;

    private TextView username;
    private TextView password;
    private Button edit;
    private Button back;

    User thisUser;
    List<User> userList;

    String userValue;
    String passValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        userList = databaseHelper.getAllUser();

        //getting data from fields on the activity account xml
        username = (TextView)findViewById(R.id.userText);
        password = (TextView)findViewById(R.id.userPass);
        edit = (Button)findViewById(R.id.b_edit_account);
        back = (Button)findViewById(R.id.b_back_to_home);

        thisUser = getUser(currentUser, userList);

        //accepts the data passed from userHomeActivity
        userValue = thisUser.getEmail();
        passValue = thisUser.getPassword();

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
                Intent intent = new Intent(AccountActivity.this, UserHomeActivity.class);
                startActivity(intent);
            }
        });
    }

    public User getUser(String email, List<User> users) {
        User thisUser = new User();
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getEmail().matches(email)) {
                thisUser.setId(users.get(i).getId());
                thisUser.setEmail(users.get(i).getEmail());
                thisUser.setPassword(users.get(i).getPassword());
                thisUser.setName(users.get(i).getName());
            }
        }
        return thisUser;
    }
}