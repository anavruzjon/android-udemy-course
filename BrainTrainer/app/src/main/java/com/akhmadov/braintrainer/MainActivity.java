package com.akhmadov.braintrainer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_layout);


    }



    public void startOnClick(View view) {
        Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
        startActivity(gameActivity);
    }


}
