package com.example.lab2_mob204.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.example.lab2_mob204.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                Intent intent= new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        }.start();
    }


    public void openHome(View view) {
        Intent intent= new Intent(WelcomeActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    }


