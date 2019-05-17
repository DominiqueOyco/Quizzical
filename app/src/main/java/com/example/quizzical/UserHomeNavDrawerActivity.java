package com.example.quizzical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

/**
 * User Home Activity with Nav Drawer (sliding settings screen), this is where the Quiz
 * takes place and where you get your score.
 */

public class UserHomeNavDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String newUser;
    String newPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        newUser = getIntent().getStringExtra("Username"); //accepts the username data from mainactivity
        newPass = getIntent().getStringExtra("Password"); //accepts the password data from mainactivity
    }

    private void startQuiz() {
        Intent intent = new Intent(UserHomeNavDrawerActivity.this, QuizActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //directs user to the account page
        if (id == R.id.nav_account){
            Intent intent = new Intent(UserHomeNavDrawerActivity.this, AccountActivity.class);
            intent.putExtra ( "New Username", newUser); //pass the accepted username data from mainactivity to the account page
            intent.putExtra ( "New Password", newPass); //pass the accepted password data from mainactivity to the account page
            startActivity(intent);

            //directs the user to the about page
        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(UserHomeNavDrawerActivity.this, AboutActivity.class);
            startActivity(intent);

            //once clicked, the user is signed out and gets directed to the login page
        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(UserHomeNavDrawerActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(UserHomeNavDrawerActivity.this,
                    "LOGOUT SUCCESSFUL! SEE YA AGAIN!", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
