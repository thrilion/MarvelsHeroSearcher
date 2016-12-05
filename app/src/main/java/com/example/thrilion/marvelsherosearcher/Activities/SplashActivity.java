package com.example.thrilion.marvelsherosearcher.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.thrilion.marvelsherosearcher.Adapters.SuperheroListAdapter;
import com.example.thrilion.marvelsherosearcher.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent().setClass(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        // Hacemos la espera de 2 segudnos
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_DELAY);
    }
}
