package com.example.quizzical;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //calls the aboutfragment class which displays the fragment that is a part of the activity about xml
        AboutFragment about = new AboutFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, about).commit();

    }
}