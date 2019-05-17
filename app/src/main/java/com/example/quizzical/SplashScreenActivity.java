package com.example.quizzical;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 *  Splash Screen
 *  - app logo
 *  - app name
 *  - progress bar
 *  Shows logo and app name and loads for 3 seconds
 */
public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_splash_screen);

        mProgress = (ProgressBar) findViewById(R.id.pb_splashscreen);

        new Thread(new Runnable(){
            public void run(){
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork(){
        for (int progress = 0; progress < 100; progress += 20){
            try{
                Thread.sleep(500);
                mProgress.setProgress(progress);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void startApp(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
