package com.akhmadov.timersdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                // Countdown is counting

                Log.d("myLogs", "Seconds until the finish: " +
                        Double.toString((double)millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                // Count finished!

                Log.d("myLogs", "Count finished!");

            }
        }.start();

        /*
        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Insert code to be run every second.
                Log.d("myLogs", "a second must have passed...");
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(runnable);*/
    }
}
