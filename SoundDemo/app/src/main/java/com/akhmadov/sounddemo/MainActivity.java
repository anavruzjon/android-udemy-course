package com.akhmadov.sounddemo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.music);

        mPlayer.start();
    }

    public void startResumeOnClick(View view) {
        
    }

    public void pauseOnClick(View view) {

    }
}
